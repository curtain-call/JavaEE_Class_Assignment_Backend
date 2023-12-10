import argparse
import sys
from sys import platform
import os
import cv2
import pyopenpose as op
from motion_est import motionEst
from utils import calculate_angle, get_angles

'''
1. 关键点定位的准确性
选择关键点：确定用于比较的主要关节和身体部位。这些通常包括手臂、腿部、脊柱和头部的关键点。
精度要求：设定关键点位置允许的最大偏差范围。这取决于动作的特性和执行的严格程度。
2. 时间序列分析
动作相位：将动作分解为不同的阶段（例如，举重的上举和下放），并分别评估每个阶段。
关键点轨迹：分析关键点随时间的运动轨迹，确保用户的动作轨迹与标准动作相似。
3. 角度和姿态分析
关节角度：计算特定动作中关键关节（如膝关节、肘关节）的角度，并与标准动作进行比较。
身体姿态：评估整体身体姿态（如背部直立、倾斜等）的正确性。
4. 运动的流畅性和连贯性
速度和节奏：动作的速度和节奏对某些健身活动至关重要。比较用户动作的速度和节奏是否与标准动作一致。
连续性：评估动作的连贯性和流畅性，避免突然的停顿或不自然的运动。
5. 综合评分系统
创建评分标准：基于上述因素，开发一个综合评分系统，为用户的每一个动作提供量化的评分。
阈值设定：设定合格的分数阈值，帮助用户了解他们的表现是否达到了基本标准。
6. 可视化反馈
差异突出显示：在用户的动作和标准动作之间的比较中，可视化地突出显示关键差异。
动态演示：提供动态演示，展示正确的动作和用户动作的对比。
'''


'''
datum:
    cvInputData:
        类型：numpy.ndarray
        描述：输入图像。这是传递给 OpenPose 处理的原始图像。
    
    poseKeypoints:
        类型：numpy.ndarray
        描述：检测到的人体姿势关键点。它是一个三维数组，维度为 [人数, 关键点数量, 3]。最后一个维度包含 x、y 坐标和置信度分数。
        
    faceKeypoints:
        类型：numpy.ndarray
        描述：检测到的面部关键点，格式与 poseKeypoints 相似。
        
    handKeypoints:
        类型：两个 numpy.ndarray 的列表
        描述：检测到的手部关键点，包括左手和右手。每个列表元素都是一个三维数组，格式与 poseKeypoints 相似。
        
    cvOutputData:
        类型：numpy.ndarray
        描述：OpenPose 处理后的输出图像。通常，这个图像会在原始输入图像上渲染关键点和骨架。
        
    netOutputSize:
        类型：(int, int)
        描述：网络输出层的大小。
    outputData:
        类型：numpy.ndarray
        描述：网络的原始输出数据。
        
    scaleNetToOutput:
        类型：float
        描述：从网络输出尺寸到最终输出图像尺寸的缩放因子。
'''




def draw_keypoints(image, keypoints):
    '''
    虽然可以画, 但是也不一定用
    :param image:
    :param keypoints:
    :return:
    '''
    for key, value in keypoints.items():
        # 绘制关键点
        for keypoint in value:
            # 检查关键点的可信度
            if keypoint[2] > 0.1:  # 可以调整阈值
                cv2.circle(image, (int(keypoint[0]), int(keypoint[1])), 3, (0, 255, 0), thickness=-1, lineType=cv2.FILLED)

    # 右肩到右肘
    if keypoints['shoulder'][0][2] > 0.1 and keypoints['elbows'][0][2] > 0.1:
        cv2.line(image, (int(keypoints['shoulder'][0][0]), int(keypoints['shoulder'][0][1])),
                 (int(keypoints['elbows'][0][0]), int(keypoints['elbows'][0][1])),(255, 0, 0), 2 )

    # 左肩到左肘
    if keypoints['shoulder'][1][2] > 0.1 and keypoints['elbows'][1][2] > 0.1:
        cv2.line(image, (int(keypoints['shoulder'][1][0]), int(keypoints['shoulder'][1][1])),
                 (int(keypoints['elbows'][1][0]), int(keypoints['elbows'][1][1])), (255, 0, 0), 2)

    # 连接右肘到右手腕
    if keypoints['elbows'][0][2] > 0.1 and keypoints['wrists'][0][2] > 0.1:
        cv2.line(image, (int(keypoints['elbows'][0][0]), int(keypoints['elbows'][0][1])),
                 (int(keypoints['wrists'][0][0]), int(keypoints['wrists'][0][1])), (255, 0, 0), 2)

    # 连接左肘到左手腕
    if keypoints['elbows'][1][2] > 0.1 and keypoints['wrists'][1][2] > 0.1:
        cv2.line(image, (int(keypoints['elbows'][1][0]), int(keypoints['elbows'][1][1])),
                 (int(keypoints['wrists'][1][0]), int(keypoints['wrists'][1][1])), (255, 0, 0), 2)

    # 连接中背部到颈部
    if keypoints['mid_back'][2] > 0.1 and keypoints['neck'][2] > 0.1:
        cv2.line(image, (int(keypoints['mid_back'][0]), int(keypoints['mid_back'][1])),
                 (int(keypoints['neck'][0]), int(keypoints['neck'][1])), (255, 0, 0), 2)

    # 连接右髋到脊柱
    if keypoints['hips'][0][2] > 0.1 and keypoints['spine'][0][2] > 0.1:
        cv2.line(image, (int(keypoints['hips'][0][0]), int(keypoints['hips'][0][1])),
                 (int(keypoints['spine'][0][0]), int(keypoints['spine'][0][1])), (255, 0, 0), 2)

    # 连接左髋到脊柱
    if keypoints['hips'][1][2] > 0.1 and keypoints['spine'][0][2] > 0.1:
        cv2.line(image, (int(keypoints['hips'][1][0]), int(keypoints['hips'][1][1])),
                 (int(keypoints['spine'][0][0]), int(keypoints['spine'][0][1])), (255, 0, 0), 2)

    # 连接右膝到右踝
    if keypoints['knees'][0][2] > 0.1 and keypoints['ankles'][0][2] > 0.1:
        cv2.line(image, (int(keypoints['knees'][0][0]), int(keypoints['knees'][0][1])),
                 (int(keypoints['ankles'][0][0]), int(keypoints['ankles'][0][1])), (255, 0, 0), 2)

    # 连接左膝到左踝
    if keypoints['knees'][1][2] > 0.1 and keypoints['ankles'][1][2] > 0.1:
        cv2.line(image, (int(keypoints['knees'][1][0]), int(keypoints['knees'][1][1])),
                 (int(keypoints['ankles'][1][0]), int(keypoints['ankles'][1][1])), (255, 0, 0), 2)

    # 连接右膝到右髋
    if keypoints['knees'][0][2] > 0.1 and keypoints['hips'][0][2] > 0.1:
        cv2.line(image, (int(keypoints['knees'][0][0]), int(keypoints['knees'][0][1])),
                 (int(keypoints['hips'][0][0]), int(keypoints['hips'][0][1])), (255, 0, 0), 2)

    # 连接左膝到左髋
    if keypoints['knees'][1][2] > 0.1 and keypoints['hips'][1][2] > 0.1:
        cv2.line(image, (int(keypoints['knees'][1][0]), int(keypoints['knees'][1][1])),
                 (int(keypoints['hips'][1][0]), int(keypoints['hips'][1][1])), (255, 0, 0), 2)

    return image


def extract_pushup_keypoints(keypoints):
    '''
    俯卧撑关键点提取(侧方位)
    :param keypoints: openpose可以提供的body25全部关键点组  ndarray
    :return: dict of key_points
    '''
    if keypoints is not None:
        for person in keypoints:
            new_keypoints = {}
            new_keypoints['shoulder'] = [person[2], person[5]]
            new_keypoints['elbows'] = [person[3], person[6]]
            new_keypoints['wrists'] = [person[4], person[7]]
            new_keypoints['hips'] = [person[9], person[12]]
            new_keypoints['neck'] = [person[1]]
            new_keypoints['spine'] = [person[8]]
            new_keypoints['knees'] = [person[10], person[13]]
            new_keypoints['ankles'] = [person[11], person[14]]
            return new_keypoints
    else:
        return None


def process_shoulder(arm_shoulder_angle, arm_shoulder_angle_st):
    pass


def process_elbow(elbow_angle, elbow_angle_st):
    pass


def process_waist(waist_angle, waist_angle_st):
    message = {}
    message['问题'] = []
    message['指导'] = []
    if abs(waist_angle - waist_angle_st) > 15:
        message['问题'].append("腰部存在下沉，形成“塌腰”姿势，这会导致腰椎过度负担")
        message['指导'].append("")
    pass


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    # parser.add_argument("--standard_example", type=str, required=True)
    # parser.add_argument("--custom_video", type=str, required=True)

    motion = motionEst([])
    motion_st = motionEst([])


    params = {"model_folder": "E:\\javaEE\\ClassAssignment\\openpose\\models\\", "model_pose": "BODY_25", "net_resolution": "-1x176"}
    openpose = op.WrapperPython()
    openpose.configure(params)
    openpose.start()

    cap_standard = cv2.VideoCapture("E:\\javaEE\\ClassAssignment\\openpose\\push_up(above).mp4")
    cap = cv2.VideoCapture("E:\\javaEE\\ClassAssignment\\openpose\\push_up(aside).mp4")
    # cap = cv2.VideoCapture(stream_url)
    while cap.isOpened() and cap_standard.isOpened():
        ret, frame = cap.read()
        ret_st, frame_st = cap_standard.read()

        # ToDo 视频不同时结束可能会导致行为不正确
        if not ret:
            break
        if not ret_st:
            break

        # 使用 OpenPose 处理custom帧
        datum = op.Datum()
        datum.cvInputData = frame
        openpose.emplaceAndPop(op.VectorDatum([datum]))

        # standard帧
        datum_st = op.Datum()
        datum_st.cvInputData = frame_st
        openpose.emplaceAndPop(op.VectorDatum([datum_st]))


        # 选择关键点
        keypoints = extract_pushup_keypoints(datum.poseKeypoints)
        keypoints_st = extract_pushup_keypoints(datum_st.poseKeypoints)

        # 需要保留的关键点加入time_seq
        motion.add_time_seq(keypoints['shoulder'][0])
        motion_st.add_time_seq(keypoints_st['shoulder'][0])

        # 重新处理帧
       
        arm_shoulder_angle, elbow_angle, waist_angle = get_angles(keypoints)
        arm_shoulder_angle_st, elbow_angle_st, waist_angle_st = get_angles(keypoints_st)
        
        process_shoulder()
        process_elbow()
        process_waist()

        # 获取处理后的帧并显示
        # 这里不要显示
        # processed_frame = datum.cvOutputData
        processed_frame = frame
        processed_frame_st = datum_st.cvOutputData
        cv2.imshow("OpenPose", processed_frame)
        cv2.imshow("standard", processed_frame_st)

        if cv2.waitKey(1) & 0xFF == ord('q'):  # 按 'q' 退出
            break

    cap.release()
    cap_standard.release()
    cv2.destroyAllWindows()



    # parse_video(stream_url)

    # try:
    #     # Windows Import
    #     if platform == "win32":
    #         # Change these variables to point to the correct folder (Release/x64 etc.)
    #         sys.path.append('E:\\javaEE\\ClassAssignment\\openpose\\build\\python\\openpose\\Release')
    #         os.environ['PATH'] = (os.environ['PATH']
    #                               # + ';' + dir_path + '/../../x64/Release;' + dir_path + '/../../bin;')
    #                               + 'E:\\javaEE\\ClassAssignment\\openpose\\build\\bin;' + 'E:\\javaEE\\ClassAssignment\\openpose\\build\\x64\\Release;')
    #         import pyopenpose as op
    #     else:
    #         # Change these variables to point to the correct folder (Release/x64 etc.)
    #         sys.path.append('../../python');
    #         # If you run `make install` (default path is `/usr/local/python` for Ubuntu), you can also access the OpenPose/python module from there. This will install OpenPose and the python library at your desired installation path. Ensure that this is in your python path in order to use it.
    #         # sys.path.append('/usr/local/python')
    #         from openpose import pyopenpose as op
    # except ImportError as e:
    #     print(
    #         'Error: OpenPose library could not be found. Did you enable `BUILD_PYTHON` in CMake and have this Python script in the right folder?')
    #     raise e


    # process_video(stream_url)
    # print(dir_path)
