package damon.zy.com.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;


/**
 * Created by Zy on 2016/11/13.
 */

public class GameView extends GridLayout {

    public GameView(Context context) {
        super(context);
        System.out.println("consucter1");
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("consucter2");
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        System.out.println("consucter3");
        initGameView();
    }

    private Card[][] cardsArray = new Card[4][4];

    private void initGameView() {
        setColumnCount(4);
        setRowCount(4);
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new View.OnTouchListener() {
            //StartX,StratY分别表示用户开始滑屏动作时记录的X,Y轴的位置
            //offsetX,offsetY,分别表示用户结束滑屏动作时记录的X,Y轴的偏移量
            private float StartX, StartY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //   MotionEvent.ACTION_DOWN：当屏幕检测到第一个触点按下之后就会触发到这个事件。
                    case MotionEvent.ACTION_DOWN:
                        StartX = event.getX();
                        StartY = event.getY();
//                        System.out.println("ACTION_DOWN,X:" + StartX + "Y:" + StartY);
                        break;
                    //MotionEvent.ACTION_UP：当最后一个触点松开时被触发。
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - StartX;
                        offsetY = event.getY() - StartY;
//                        System.out.println("ACTION_DOWN,offsetX:" + offsetX + "offsetY:" + offsetY);
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {

                            if (offsetX < -4) {
                                System.out.println("left");
                                SwipeLeft();
                            } else if (offsetX > 4) {
                                System.out.println("right");
                                SwipeRight();
                            }
                        } else {
                            if (offsetY < -4) {

                                System.out.println("up");
                                SwipeUp();
                            } else if (offsetY > 4)
                                System.out.println("down");

                            SwipeDown();
                        }
                        break;
                }
                return true;
            }
        });

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth = (Math.min(w, h) - 10) / 4;
        addCards(cardWidth, cardWidth);
    }

    private void addCards(int CardWidth, int CradHeight) {
        Card c;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                c = new Card(getContext());
                c.setNum(0);
                addView(c, CardWidth, CradHeight);
                cardsArray[x][y] = c;
//                System.out.println("x:" + x + "y:" + y + "c:" + c.getNum());
            }
        }
        randomAddNumInCardArray(cardsArray, 2);
    }

    private Card[][] randomAddNumInCardArray(Card[][] cardsArray, int num) {
        for (int i = 0; i < 2; ) {
            double ar = (Math.random() * 10000), br = (Math.random() * 10000);
//            System.out.println("ar:" + ar + "br:" + br);
            int a = (int) (ar % 4), b = (int) (br % 4);
//            System.out.println("a:" + a + "b:" + b);
//            if (cardsArray[a][b].getNum() == num) continue;
            if (cardsArray[a][b].getNum() != 0) continue;
            else {
                cardsArray[a][b].setNum(num);
                i++;
            }
        }
        return cardsArray;
    }

    private void SwipeLeft() {
    }

    private void SwipeRight() {
    }

    private void SwipeUp() {
    }

    private void SwipeDown() {
    }
}
