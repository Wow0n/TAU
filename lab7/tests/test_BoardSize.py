import pytest

from classes.Board import Board


class TestBoardSizes:
    @pytest.mark.parametrize("size", [-3, 0, 1, 4])
    def test_init_size_error(self, size):
        with pytest.raises(SystemExit):
            Board(size)

    @pytest.mark.parametrize("size", [5, 6, 7, 8])
    def test_board_size(self, size):
        board = Board(size)
        assert board.size == size

    @pytest.mark.xfail(raises=SystemExit, reason="Próba utworzenia za małej planszy")
    def test_board_generate_too_small(self):
        Board(4)

    @pytest.mark.skip(reason="Próba utworzenia bezsensownie dużej planszy")
    def test_board_generate_unnecessarily_too_big(self):
        Board(50)
