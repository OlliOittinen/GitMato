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


    //joo ihan kiva mut ei
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
     * Shortens the opposing worm's tail by one quarter.
     * Rewards player who picked up the powerup icon with 50 points per tailpiece cut.
     * Reduces the points from the opposing worm.
     * @param worm the Worm object that picked up the icon
     * @param worm2 the Worm object that is being targeted
     */
    public void cut(Worm worm, Worm worm2) {
        int currentLength = worm2.getTails().size();
        int howMuchAfterCut = (int) (worm2.getTails().size()*0.75);
        System.out.println(currentLength);
        System.out.println(howMuchAfterCut);
        if (worm2.getTails().size() > 0) {
            do {
                worm2.getTails().remove(currentLength - 1);
                worm.setPoints(worm.getPoints() + 50);
                worm2.setPoints(worm2.getPoints() - 50);
                currentLength = worm2.getTails().size();
            } while (currentLength > howMuchAfterCut);
        }
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
