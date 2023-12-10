class motionEst:
    def __init__(self, time_seq: list):
        self.time_seq = time_seq


    def add_time_seq(self, item):
        self.time_seq.append(item)
        return