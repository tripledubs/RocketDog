import javafx.scene.input.KeyEvent;

public class WASDKeyMapping implements KeyMapping{
    
    @Override
    public void handleKeyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getCode()){    
                    case W:
			//Move Up
			break;
                    case S:
			//Move DOWN
			break;
                    case A:
			//Move LEFT
			break;
                    case D:
			//Move Right
			break;
        }
    }

    
}
