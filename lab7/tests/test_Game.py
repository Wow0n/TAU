import pytest

from classes.Game import Game


class TestGame:
    def setup_class(self):
        print("\n====Setup====")
        self.game = Game(5)
        self.game.board.display()
        print("====Setup End====\n")

    def teardown_method(self):
        print()
        self.game.board.display()
        self.game.back_to_start()
        print("====Teardown====")

    def test_move_far_right(self):
        for i in range(0, 6):
            pytest.mark.skipif(self.game.move.right(), reason='Uderzono w przeszkodę lub próba opuszczenia planszy')

    def test_move_far_left(self):
        for i in range(0, 6):
            pytest.mark.skipif(self.game.move.left(), reason='Uderzono w przeszkodę lub próba opuszczenia planszy')

    def test_move_far_up(self):
        for i in range(0, 6):
            pytest.mark.skipif(self.game.move.up(), reason='Uderzono w przeszkodę lub próba opuszczenia planszy')

    def test_move_far_down(self):
        for i in range(0, 6):
            pytest.mark.skipif(self.game.move.down(), reason='Uderzono w przeszkodę lub próba opuszczenia planszy')

    @pytest.mark.xfail(raises=SystemExit, reason="Wygrana")
    def test_win(self):
        for i in range(0, 100):
            self.test_move_far_up()
            self.test_move_far_down()
            self.test_move_far_right()
            self.test_move_far_left()
