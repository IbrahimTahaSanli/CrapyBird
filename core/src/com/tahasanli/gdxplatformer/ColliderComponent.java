package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Shape2D;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ColliderComponent extends Component{
    public static final String Name = "ColliderComponent";
    public int Code = 3;

    public ArrayList<ColliderComponent> RegisteredCollisionObjects;

    //Shape2D class wont extended by shapes ??
    //private Shape2D shape;
    public Circle shape;

    public ColliderComponent(Circle shape){
        super();

        super.Code = Code;

        this.shape = shape;
        this.RegisteredCollisionObjects = new ArrayList<>();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onFrame(SpriteBatch batch) {
        this.shape.set(
            this.parent.Position.x - (this.shape.radius * this.parent.PivotPoint.x) ,
            this.parent.Position.y - (this.shape.radius * this.parent.PivotPoint.y) ,
            this.parent.Scale.x * this.shape.radius);

        for(ColliderComponent comp : RegisteredCollisionObjects)
            if(this.shape.overlaps(comp.shape))
                this.parent.OnCollision(comp.parent);

    }
}
