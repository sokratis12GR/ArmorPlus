package com.sofodev.armorplus.registry.item.armor;

import net.minecraft.world.entity.EquipmentSlot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SetRequirementTest {

    @Test
    void parsesSlots() {
        SetRequirement requirement = SetRequirement.parse("legs,feet");
        assertEquals(Set.of(EquipmentSlot.LEGS, EquipmentSlot.FEET), requirement.slots());
        assertEquals(2, requirement.required());
        assertTrue(requirement.slotBound());
    }

    @Test
    void parsesSlotsWithCount() {
        SetRequirement requirement = SetRequirement.parse("chest,legs,feet:2");
        assertEquals(Set.of(EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET), requirement.slots());
        assertEquals(2, requirement.required());
    }

    @Test
    void parsesPieceCount() {
        SetRequirement requirement = SetRequirement.parse("2");
        assertFalse(requirement.slotBound());
        assertEquals(2, requirement.required());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "0", "5", "hands", "head,", "head,hands", "head:2", "head:0", "head:x", "mainhand"})
    void rejectsInvalidSuffixes(String suffix) {
        assertNull(SetRequirement.parse(suffix));
        assertFalse(SetRequirement.isValid(suffix));
    }

    @ParameterizedTest
    @ValueSource(strings = {"head", "legs,feet", "chest,legs,feet:2", "2", "head,chest,legs,feet"})
    void configFormParsesBackToTheSameRequirement(String suffix) {
        SetRequirement requirement = SetRequirement.parse(suffix);
        assertEquals(requirement, SetRequirement.parse(requirement.toString()));
    }

    @Test
    void piecesAreClampedToWhatIsPossible() {
        assertEquals(ArmorEffects.SET_SIZE, SetRequirement.pieces(99).required());
        assertEquals(1, SetRequirement.pieces(0).required());
        assertEquals(1, new SetRequirement(Set.of(EquipmentSlot.HEAD), 3).required());
    }

    @Test
    void shownOnlyOnItsOwnPieces() {
        SetRequirement head = SetRequirement.slots(EquipmentSlot.HEAD);
        assertTrue(head.shownOn(EquipmentSlot.HEAD));
        assertFalse(head.shownOn(EquipmentSlot.FEET));
        assertTrue(SetRequirement.pieces(2).shownOn(EquipmentSlot.FEET));
    }
}
