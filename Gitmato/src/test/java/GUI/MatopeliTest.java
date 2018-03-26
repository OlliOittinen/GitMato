package GUI;

import javafx.scene.Parent;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;

import java.io.IOException;

import static org.junit.Assert.*;

public class MatopeliTest {

    private static GuiTest controller;

    @Before
    public void setUp() throws Exception {
        FXTestUtils.launchApp(Matopeli.class);

        controller = new GuiTest() {
            @Override
            protected Parent getRootNode() {
                Parent parent = null;
                try {
                    Matopeli app = new Matopeli();
                    parent = app.getWindow();
                    return parent;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return parent;
            }
        };
    }

    @Test
    public void main() {
    }

    @Test
    public void start() {


    }
}