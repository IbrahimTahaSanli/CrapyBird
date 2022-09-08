package com.tahasanli.gdxplatformer;

public class Character extends GameObject {

    public MoveComponent moveComponent;
    public InputComponent inputComponent;


    public Character(String name) {
        super(name);

    }

    public void AddComponent(Component component){
        component.parent = this;
        this.components.add(component);

        if (component.Code == ComponentCode.MoveComp.value)
            moveComponent = (MoveComponent) component;
        else if (component.Code == ComponentCode.InputComp.value)
            inputComponent = (InputComponent) component;

        this.SortComponent();
    }

    public void OutsideOfScreen(){
        GDXPlatformer.instance.GameFinish();
    }

    @Override
    public void OnCollision(GameObject other){
        GDXPlatformer.instance.GameFinish();
    }
}
