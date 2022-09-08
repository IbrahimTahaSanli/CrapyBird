package com.tahasanli.gdxplatformer;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.IntAction;
import com.badlogic.gdx.utils.Null;

import java.util.ArrayList;

public class GameObject {
    public String Name;

    public Vector2 Position;
    public Vector2 Scale;
    public Vector2 PivotPoint;

    public ArrayList<Component> components;

    public GameObject parent;

    public GameObject(String name){
        this.Name = name;

        Position = new Vector2();
        Scale = new Vector2(1,1);
        PivotPoint = new Vector2();

        this.components = new ArrayList<>();
    }

    public void AddComponent(Component component){
        component.parent = this;
        this.components.add(component);

        this.SortComponent();
    }

    public Component GetComponent(ComponentCode code){
        for(Component comp : this.components)
            if(comp.Code == code.value)
                return comp;
        return null;
    }

    public void SortComponent(){
        ArrayList<Component> tmp = new ArrayList<>();

        while(components.size() != 0){
            int ind = 0;

            for(int i = 1; i < components.size(); i++)
                if(components.get(i).Code < components.get(ind).Code)
                    ind = i;

            tmp.add(components.get(ind));
            components.remove(ind);
        }

        this.components = tmp;

    }

    public void OnCollision(GameObject inter){
        System.out.println(inter.Name);
    }

    public float GetWidth(){
        return ((TextureComponent)GetComponent(ComponentCode.TextureComp)).texture.getWidth();
    }


    public float GetHeight(){
        return ((TextureComponent)GetComponent(ComponentCode.TextureComp)).texture.getHeight();
    }
}
