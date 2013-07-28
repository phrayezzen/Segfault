package segfault.layout;

import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager2;


/**
 * The SplitLayout provides an interface in which
 * one can work on multiple sliding panes, without
 * dealing with the recursiveness of JSplitPanes.
 *
 * The bulk of the organization resides in the SplitNode,
 * providing a tree model that maps well with the idea
 * of nested JSplitPanes. A further explanation can be
 * found in the doc before the class definition.
 *
 * @author: Joshua Potter
 * @version: 0.1
*/ 
public class SplitLayout implements LayoutManager2 {

    /**
     * The root is the only node that does not have
     * any component associated with it. This allows
     * easier reasoning in insertions and removals
     * as the root never has to change.
    */
    private SplitNode root;

    // Mirrors SwingConstants/JSplitPane constants
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    public SplitLayout() {
        root = new SplitNode();
    }

    /** 
     * Insertion Methods
     *
    */
    @Override
    public void addLayoutComponent(String name, Component c) {
        // N/A
    }

    @Override
    public void addLayoutComponent(Component c, Object constraints) {
        if(constraints instanceof SplitConstraints) {
            // Get info
            SplitConstraints s = (SplitConstraints) constraints;
            Component split = s.getComponent();
            int orientation = s.getOrientation();

            root.split(split, c, orientation);
        } else {
            throw new IllegalArgumentException("Must use SplitConstraints with a SplitLayout");
        }
    }

    /** 
     * Removal Methods
     *
    */
    @Override
    public void removeLayoutComponent(Component c) {
        root.removeNode(c);
    }

    /** 
     * Alignment methods
     *
     * The components should always be aligned center- they reclaim any unused
     * space (similar to a vertical or horizontal box layout but in all directions)
    */
    @Override
    public float getLayoutAlignmentX(Container parent) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container parent) {
        return 0.5f;
    }

    /**
     * Layout Methods
    */
    @Override
    public void layoutContainer(Container parent) {
        // Position and size components in container
        // invokes setSize, setLocation, setBounds
        // must check getInsets and componentorientation
    }

    @Override
    public void invalidateLayout(Container parent) {
        root = new SplitNode();
    }

    /**
     * Size Methods
     *
     * Recursively builds up the size of the layout by 
     * going through left and right subtrees. 
    */
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        //Size of left and right nodes of model
        return null;
    }

    @Override
    public Dimension maximumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

}
