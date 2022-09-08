package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UITextComponent extends Component{
    public static final String Name = "UITextComponent";
    public int Code = 6;

    public BitmapFont font;
    public String text;

    public UITextComponent(Color c, int size){
        super.Code = Code;

        font = new BitmapFont();
        font.setColor(c);
        font.getData().setScale(size);
    }


    public UITextComponent(String text, Color c, int size){
        super.Code = Code;

        font = new BitmapFont();
        font.setColor(c);
        font.getData().setScale(size);

        this.text = text;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onFrame(SpriteBatch batch) {
        this.font.draw(batch, text, this.parent.Position.x - (this.parent.PivotPoint.x * font.getCapHeight() / 3 * 2 * this.text.length()), this.parent.Position.y - (this.parent.PivotPoint.y * font.getCapHeight()));
    }
}
