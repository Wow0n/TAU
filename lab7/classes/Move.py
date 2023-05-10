import sys


class Move:
    def __init__(self, board):
        self.board = board
        self.position = board.start

    def up(self):
        self.position[0] -= 1

        if self.checks():
            self.board.place(self.position, "#")
            return True
        else:
            self.position[0] += 1
            return False

    def down(self):
        self.position[0] += 1

        if self.checks():
            self.board.place(self.position, "#")
            return True
        else:
            self.position[0] -= 1
            return False

    def right(self):
        self.position[1] += 1

        if self.checks():
            self.board.place(self.position, "#")
            return True
        else:
            self.position[1] -= 1
            return False

    def left(self):
        self.position[1] -= 1

        if self.checks():
            self.board.place(self.position, "#")
            return True
        else:
            self.position[1] += 1
            return False

    def checks(self):
        if not self.out_of_bounds_check():
            return False
        if not self.obstacle_check():
            return False
        if not self.uroboros_check():
            return False
        if not self.back_to_start_check():
            return False

        self.win_check()
        return True

    def out_of_bounds_check(self):
        if self.position[0] < 0 or self.position[1] < 0 or self.position[0] > self.board.size - 1 or self.position[
            1] > self.board.size - 1:
            return False
        return True

    def obstacle_check(self):
        if self.board.get_place_value(self.position) == "X":
            return False
        return True

    def uroboros_check(self):
        if self.board.get_place_value(self.position) == "#":
            return False
        return True

    def back_to_start_check(self):
        if self.board.get_place_value(self.position) == "S":
            return False
        return True

    def win_check(self):
        if self.board.get_place_value(self.position) == "E":
            print(self.board.display())
            sys.exit("Wygrana!")
