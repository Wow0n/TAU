import random
import sys


class Board:
    def __init__(self, size):
        self.size = size
        self.start = []
        self.end = []

        if self.size < 5:
            sys.exit('Rozmiar planszy musi być większy lub równy 5x5.')

        self.board = [['-' for i in range(self.size)] for j in range(self.size)]
        self.generate_obstacles()
        self.generate_start_end()

    def display(self):
        print()
        for i in range(self.size):
            for j in range(self.size):
                print(self.board[i][j], end=' ')
            print("")
        print()

    def place(self, point, mark):
        if self.board[point[0]][point[1]] == '-':
            self.board[point[0]][point[1]] = mark
        elif self.board[point[0]][point[1]] == 'E':
            sys.exit("Wygrałeś!")
        else:
            raise ValueError

    def generate_random_point(self):
        row = random.randint(0, self.size - 1)
        col = random.randint(0, self.size - 1)

        while self.board[row][col] != '-':
            row = random.randint(0, self.size - 1)
            col = random.randint(0, self.size - 1)

        return row, col

    def points(self):
        if random.random() < 0.5:
            row = 0 if random.random() < 0.5 else self.size - 1
            col = random.randint(0, self.size - 1)
        else:
            row = random.randint(0, self.size - 1)
            col = 0 if random.random() < 0.5 else self.size - 1

        return row, col

    def generate_border_point(self):
        row, col = self.points()
        while self.board[row][col] != '-':
            row, col = self.points()

        return row, col

    def generate_obstacles(self):
        for i in range((self.size // 2) + 1):
            self.place(self.generate_random_point(), "X")

    def generate_start_end(self):
        start_row, start_col = self.generate_border_point()

        for i in range(100):
            end_row, end_col = self.generate_border_point()
            if abs(start_row - end_row) > 1 and abs(start_col - end_col) > 1:
                break
        else:
            sys.exit("Nie można wygenerować punktu startu i końca")

        self.start = [start_row, start_col]
        self.end = [end_row, end_col]

        self.place(self.start, "S")
        self.place(self.end, "E")

    def get_place_value(self, point):
        return self.board[point[0]][point[1]]
