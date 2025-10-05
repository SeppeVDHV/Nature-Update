package net.seppevdh.natureupdate.block.state;

import net.minecraft.util.StringRepresentable;

public enum ShelfType implements StringRepresentable {
    UNPOWERED("unpowered"),
    LEFT("powered_left"),
    MIDDLE("powered_middle"),
    SINGLE("powered_single"),
    RIGHT("powered_right");

    private final String name;

    ShelfType(final String pName) {
        this.name = pName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
