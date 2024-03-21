/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        // setB.add(11);
        assertThrows(IllegalArgumentException.class, () -> setB.add(11), "add: adding an element already in the set should throw an exception.");
        assertFalse(setB.contains(11), "add: added element not found in set.");
        assertNotEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Test
    void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    void testAddDuplicate() {
        setA = new BoundedSetOfNaturals(3);
        setA.add(99);
        assertThrows(IllegalArgumentException.class, () -> setA.add(99), "add duplicate: duplicate element added.");
    }

    @Test
    void testAddNonNatural() {
        assertThrows(IllegalArgumentException.class, () -> setA.add(0), "non natural element add: non natural element added.");
    }

    @Test
    void testIntersection() {
        assertTrue(setB.intersects(BoundedSetOfNaturals.fromArray(new int[]{20, 30, 40})));
        assertFalse(setB.intersects(BoundedSetOfNaturals.fromArray(new int[]{20, 30, 45})));
        assertFalse(setC.intersects(setB));
        assertTrue(setB.intersects(setC));
    }

    @Test 
    void testEquals(){
        assertEquals(setA, setA, "equals: set should be equal to itself");
        assertNotEquals(setA, setB, "equals: set should not be equal to another set");

        assertNotEquals(setA, null, "equals: set should not be equal to null");

        assertNotEquals(setB, 20);

    }

    @Test
    void testHashCode(){
        assertEquals(setA.hashCode(), setA.hashCode(), "hashCode: hash code of the same set should be the same");
        assertNotEquals(setB.hashCode(), setA.hashCode(), "hashCode: hash code of the same set shouldn't be the same");
    }


}
