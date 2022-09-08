package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class EnemyComponent extends Component{
    public static final String Name = "EnemyComponent";
    public int Code = 7;

    public static final float EnemySpeed = 5f;

    private int RowCount;
    private float RowHeight;

    public EnemyComponent(int rowCount, float rowHeight){
        this.RowCount = rowCount;
        this.RowHeight = rowHeight;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onFrame(SpriteBatch batch) {
        if(this.parent.Position.x < 0) {
            this.parent.Position.x = Gdx.graphics.getWidth();
            this.parent.Position.y = this.RowHeight * ((int)(new Random().nextFloat() * this.RowCount));
            GDXPlatformer.instance.Score += 1;
        }

        this.parent.Position.x -= EnemySpeed;
    }
}
