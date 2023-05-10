import pytest

from classes.Board import Board


class TestBoard:
    @classmethod
    def setup_class(cls):
        print("====SetupClass====")
        cls.board = Board(5)
        cls.board.display()

    @classmethod
    def teardown_class(cls):
        print()
        cls.board.display()
        cls.board = None
        print("====TeardownClass====")

    @pytest.mark.parametrize("point, mark", [((0, 0), "#"), ((1, 1), "#"), ((2, 2), "#"), ((3, 3), "#"), ((4, 4), "#")])
    def test_board_place(self, point, mark):
        try:
            self.board.place(point, mark)
        except ValueError:
            print("Trawiono w przeszkodę/start/koniec")
            pass
        else:
            assert self.board.get_place_value(point) == mark

    def test_generate_obstacles(self):
        obstacles_count = sum(row.count("X") for row in self.board.board)
        assert obstacles_count == (self.board.size // 2) + 1
