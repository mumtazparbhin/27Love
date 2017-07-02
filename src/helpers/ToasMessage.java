package helpers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ToasMessage {

	public static void makeText(Label label, String message, EventHandler<ActionEvent> eventHandler){
		label.setBackground(new Background(new BackgroundFill(Color.GRAY,new CornerRadii(0.3),null)));
		label.setText(message);
		label.setVisible(true);
		FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);

		ft.play();
		if(eventHandler != null) {
			ft.setOnFinished(eventHandler);
		}


	}

}
