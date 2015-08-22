package custom;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * Created by rifat on 8/4/15.
 */
public class TableCallBack implements Callback<TableColumn<Object, Object>, TableCell<Object, Object>> {

    Callback<TableColumn<Object, Object>, TableCell<Object, Object>> callback = new Callback<TableColumn<Object, Object>, TableCell<Object, Object>>() {
        @Override
        public TableCell<Object, Object> call(TableColumn<Object, Object> param) {
            final TableCell cell = new TableCell() {


                public void updateItem(Object item, boolean empty) {
                    Text text = new Text();
                    super.updateItem(item, empty);
                    if (!isEmpty()) {
                        text = new Text(item.toString());
                        text.setWrappingWidth(200);
                        text.setFill(Color.BLACK);
                        setGraphic(text);
                    } else if (isEmpty()) {
                        text.setText(null);
                        setGraphic(null);
                    }
                }
            };
            return cell;
        }
    };

    @Override
    public TableCell<Object, Object> call(TableColumn<Object, Object> param) {
        return null;
    }
}
