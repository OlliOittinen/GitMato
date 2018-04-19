package Spawnables;


import Model.Worm;
import Sound.Music;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import Model.Board;



/**
 *
 * @author Max
 */
public class Cut extends AbstractSpawnables{

    /**
     * Awards worm object with points, slows down the other worm.
     * Plays the corresponding music.
     * @see Model.WormStates.WormSlow
     */
    //requires both worms for correct application
    public void cut(Board board, int who) {
        //who == 1 means that player 1 is the target of the cutting
        if(who == 1 && board.getTailList().size() > 0){
            int originalLength = board.getTailList().size();
            while(board.getTailList().size()*2 > originalLength){
                board.getTailList().remove(board.getTailList().size()-1);
                board.setTailNro(board.getTailNro()-1);
                board.getWorm().setPoints(board.getWorm().getPoints()-50);
                board.getWorm2().setPoints(board.getWorm2().getPoints()+50);
            }
        }else if(who == 2 && board.getTailList2().size() > 0){
            int originalLength2 = board.getTailList2().size();
            while (board.getTailList2().size() * 2 > originalLength2) {
                board.getTailList2().remove(board.getTailList2().size() - 1);
                board.setTailNro2(board.getTailNro2()-1);
                board.getWorm2().setPoints(board.getWorm2().getPoints()-50);
                board.getWorm().setPoints(board.getWorm().getPoints()+50);
            }
        }


        //add points to the one who picked up


    }

    /**
     * Class constructor, calls on init();
     * @see AbstractSpawnables
     */
    public Cut() {
        init();
    }

    @Override
    public Bounds getBoundsForIcon() {
        Rectangle icon = new Rectangle(x, y, 55, 42);
        return icon.getLayoutBounds();
    }

}
