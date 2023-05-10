from classes.Game import Game

if __name__ == '__main__':
    game = Game(5)
    game.start()

    key = input()
    while key != 'q':
        if key == "w":
            game.move.up()
        elif key == "a":
            game.move.left()
        elif key == "s":
            game.move.down()
        elif key == "d":
            game.move.right()
        elif key == "c":
            game.back_to_start()
        else:
            print("Zły przycisk!")

        game.board.display()
        key = input()
