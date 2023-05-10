from classes.Board import Board
from classes.Move import Move


class Game:
    def __init__(self, size):
        self.board = Board(size)
        self.move = Move(self.board)

    def start(self):
        self.board.display()

    def back_to_start(self):
        for i in range(self.board.size):
            for j in range(self.board.size):
                if self.board.board[i][j] == 'S':
                    self.move.position = [i, j]
                elif self.board.board[i][j] not in ['S', 'E', 'X']:
                    self.board.board[i][j] = '-'
