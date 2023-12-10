import numpy as np

def calculate_angle(pt1, pt2, pt3):
    '''
    计算角度的工程方法
    :param pt1: 边点坐标1
    :param pt2: 角点坐标
    :param pt3: 边点坐标2
    :return: 角度
    '''
    # 向量 BA 和 BC
    ba = [pt1[0] - pt2[0], pt1[1] - pt2[1]]
    bc = [pt3[0] - pt2[0], pt3[1] - pt2[1]]

    # 计算两向量的点积
    dot_product = np.dot(ba, bc)

    # 计算两向量的模
    magnitude_ba = np.linalg.norm(ba)
    magnitude_bc = np.linalg.norm(bc)

    # 计算夹角的余弦值
    cosine_angle = dot_product / (magnitude_ba * magnitude_bc)

    # 转换为角度
    angle = np.arccos(cosine_angle)

    # 转换为度数
    angle_deg = np.degrees(angle)

    return angle_deg


def get_angles(keypoints):
    arm_shoulder_angle = calculate_angle(keypoints['elbows'][0],
                                         keypoints['shoulders'][0],
                                         keypoints['wrists'][0])

    elbow_angle = calculate_angle(keypoints['shoulders'][0],
                                  keypoints['elbows'][0],
                                  keypoints['wrists'][0])

    waist_angle = calculate_angle(keypoints['hips'][0],
                                  keypoints['mid_back'][0],
                                  keypoints['shoulders'][0])
    return arm_shoulder_angle, elbow_angle, waist_angle
