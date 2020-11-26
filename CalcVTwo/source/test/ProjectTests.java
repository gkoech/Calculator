package source.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import source.*;
import source.structures.*;


// these are the same as public tests
public class ProjectTests {

  Calculator calc = new Calculator();
  QueList<String> stringQue;
  QueList<Double> doubleQue;
  Stack<String> stringStack;
  Stack<Double> doubleStack;
  BinarySearchTree<String> stringTree;
  BinarySearchTree<Double> doubleTree;
  BinarySearchTree<Integer> intTree;

  @Before
  public void testSetup(){
    stringQue = new QueList<String>();
    doubleQue = new QueList<Double>();
    stringStack = new Stack<String>();
    doubleStack = new Stack<Double>();
    stringTree = new BinarySearchTree<String>();
    doubleTree = new BinarySearchTree<Double>();
    intTree = new BinarySearchTree<Integer>();
  }

  @Test
  public void testQue(){
    // testing push.
    stringQue.push("The first element");
    stringQue.push("The second element");
    stringQue.push("The third element");
    stringQue.push("The fourth element");
    assertTrue(stringQue.size() == 4);
    assertEquals("The first element", stringQue.getAt(0));
    assertEquals("The second element", stringQue.getAt(1));
    assertEquals("The third element", stringQue.getAt(2));
    assertEquals("The fourth element", stringQue.getAt(3));
    assertEquals(4, stringQue.size());
    // reset test
    stringQue.reset();
    assertEquals(0, stringQue.size());
  }

  @Test (expected = NullPointerException.class)
  public void testQue2(){
    stringQue.push(null);
    stringQue.getAt(0);
    stringQue.removeFirst();
    stringQue.removeLast();
    stringQue.push("Go Mila");
    stringQue.removeAt(1);
    stringQue.removeLast();
    stringQue.getFirst();
    stringQue.getLast();
    stringQue.getAt(1);
    stringQue.push("Hello");
    stringQue.changeAt(1, "Something just like this");
    stringQue.changeAt(20, "Howdy");
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testQue3(){
    stringQue.push("First element");
    stringQue.push("Second element");
    stringQue.push("Third element");
    stringQue.push("Fourth element");
    stringQue.getAt(6);
    stringQue.getAt(-1);
    stringQue.removeAt(6);
    stringQue.changeAt(6, "Twenty thousand");
  }

  @Test
  public void testQue4(){
    doubleQue.push(1.0);
    doubleQue.changeAt(0, 1.8);
    assertTrue(1.8 == doubleQue.getFirst());
  }

  @Test
  public void testQue5(){
    // testing insertFirst
    stringQue.insertFirst("Seven");
    stringQue.insertFirst("Six");
    stringQue.insertFirst("Five");
    stringQue.insertFirst("Four");
    stringQue.insertFirst("three");
    stringQue.insertFirst("two");
    stringQue.insertFirst("one");
    assertEquals(7, stringQue.size());
    assertEquals("one" ,stringQue.removeFirst());
    assertEquals(6, stringQue.size());
  }

  @Test
  public void testQue6(){
    stringQue.push("I've been reading books of old");
    stringQue.push("the legends and the myths");
    stringQue.push("Achillies and his gold...");
    stringQue.push("Hercules and his gifts!");
    stringQue.push("Spiderman's control");
    stringQue.push("Batman and his fists");
    assertEquals(6, stringQue.size());
    //removeFirst test
    assertEquals("I've been reading books of old", stringQue.removeFirst());
    assertEquals(5, stringQue.size());
    assertEquals("the legends and the myths", stringQue.removeFirst());
    // removeAt test
    assertEquals(4, stringQue.size());
    assertEquals("Hercules and his gifts!", stringQue.removeAt(1));
    assertEquals(3, stringQue.size());
  }

  @Test
  public void testQue7(){
    stringQue.insertFirst("One");
    stringQue.push("Two");
    stringQue.push("Three");
    stringQue.push("Five");
    stringQue.push("Six");
    stringQue.push("Seven");
    stringQue.push(3, "Four");
    assertEquals(7, stringQue.size());
    assertEquals("Four", stringQue.getAt(3));
    // removeFirst test
    assertEquals("One", stringQue.removeFirst());
    assertEquals(6, stringQue.size());
    stringQue.insertFirst("One");
    //removeAt test
    String f = stringQue.removeAt(4);
    assertEquals("Five", f);
    //indexOf test
    stringQue.reset();
    stringQue.insertFirst("One");
    stringQue.push("Two");
    stringQue.push("Three");
    stringQue.push("Five");
    stringQue.push("Six");
    stringQue.push("Seven");
    stringQue.push(3, "Four");
    assertEquals(-1, stringQue.indexOf("I am not greatness"));
    assertEquals(3, stringQue.indexOf("Four"));
    // getLast test
    assertEquals("Seven", stringQue.getLast());
    // changeAt test
    stringQue.changeAt(3, "I am no longer Four!");
    assertEquals("I am no longer Four!", stringQue.getAt(3));
  }

  @Test
  public void testStack(){
    doubleStack.push(1.1);
    doubleStack.push(1.2);
    doubleStack.push(1.3);
    doubleStack.push(1.4);
    doubleStack.push(1.5);
    assertTrue(doubleStack.peek() == 1.5);
    assertTrue(doubleStack.peek(4) == 1.1);
    assertEquals(doubleStack.peek(), doubleStack.pull());
    doubleStack.reset();
    assertEquals(0, doubleStack.size());
  }

  @Test (expected = NullPointerException.class)
  public void testStack2(){
    doubleStack.push(null);
    doubleStack.pull();
    doubleStack.peek();
    doubleStack.peek(0);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testStack3(){
    doubleStack.push(1.1);
    doubleStack.peek(3);
    doubleStack.push(12.2);
    doubleStack.peek(-1);
  }



  //-----------------Binary Search Tree Tests-------------------//


  // testing size method
  @Test
  public void testBST1(){
    intTree.add(6);
    intTree.add(4);
    intTree.add(9);
    intTree.add(8);
    intTree.add(4);
    intTree.add(5);
    intTree.add(7);
    assertEquals(intTree.size(), 7);
  }

  // testing isEmpty method
  @Test
  public void testBST2(){
    assertEquals(-1, intTree.height());
    assertEquals(true,intTree.isEmpty());
    assertEquals(null, intTree.getMinimum());
    assertEquals(null, intTree.getMaximum());
    intTree.add(1000);
    intTree.add(1);
    assertEquals(2, intTree.size());
    assertEquals(1, intTree.height());
    assertTrue(1 == intTree.getMinimum());
    assertTrue(1000 == intTree.getMaximum());
  }


  // Iterator tests
  // inorder iterator
  @Test
  public void testBST3(){
    intTree.add(10);
    intTree.add(9);
    intTree.add(8);
    intTree.add(7);
    intTree.add(6);
    intTree.add(5);
    intTree.add(4);
    intTree.add(3);
    intTree.add(2);
    intTree.add(1);
    intTree.add(11);
    intTree.add(12);
    intTree.add(13);
    intTree.add(14);
    intTree.add(15);
    intTree.add(16);
    intTree.add(17);
    intTree.add(18);
    intTree.add(19);
    intTree.add(20);
    Iterator<Integer> inOrder = intTree.inorderIterator();
    int thisVal = intTree.getMinimum();
    // I BUILT MY OWN ITERATOR!! WOO!!
    int iteratorVal = 0;
    while(inOrder.hasNext() == true){
      iteratorVal = inOrder.next();
      assertTrue( Integer.toString(thisVal) + " is being compared to", (thisVal == iteratorVal));
      thisVal = thisVal + 1;
    }
    thisVal = thisVal - 1;
    assertTrue(intTree.getMaximum() == iteratorVal);
    assertTrue(intTree.getMaximum() == thisVal);
  }

  // preorder itarator
  /**
   * Testing integer tree
   */
  @Test
  public void testBST4(){
    intTree.add(10);
    intTree.add(1);
    intTree.add(2);
    intTree.add(4);
    intTree.add(3);
    intTree.add(4);
    intTree.add(8);
    intTree.add(5);
    intTree.add(6);
    intTree.add(7);
    intTree.add(9);
    intTree.add(0);
    assertTrue("Checking for min val in tree", intTree.getMinimum() == 0);
    assertTrue(intTree.getMaximum() == 10);
    assertFalse(intTree.isBalanced());
  }

  @Test
  public void testBST5(){
    int tempIntTreeVal = 10;
    int intTreeVal = 0;
    BinarySearchTree<Integer> tempIntTree = new BinarySearchTree<Integer>();
    boolean done = false;
    while(done == false){
      intTree.add(intTreeVal);
      tempIntTree.add(tempIntTreeVal);
      if((tempIntTreeVal == 0) && (intTreeVal == 10)){
        done = true;
      }
      intTreeVal = intTreeVal + 1;
      tempIntTreeVal = tempIntTreeVal - 1;
    }
    assertFalse(tempIntTree.equals(intTree));
    assertTrue(tempIntTree.sameValues(intTree));
    tempIntTree.balance();
    intTree.balance();
    assertTrue(tempIntTree.equals(intTree));
  }
}