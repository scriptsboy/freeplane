package org.freeplane.view.swing.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import org.freeplane.features.mode.ModeController;

@SuppressWarnings("serial")
abstract public class ShapedMainView extends MainView {
	@Override
    public
	Point getLeftPoint() {
		final Point in = new Point(0, getHeight() / 2);
		return in;
	}

	@Override
    public
	Point getRightPoint() {
		final Point in = getLeftPoint();
		in.x = getWidth() - 1;
		return in;
	}

	@Override
	public void paintComponent(final Graphics graphics) {
		final Graphics2D g = (Graphics2D) graphics;
		final NodeView nodeView = getNodeView();
		if (nodeView.getModel() == null) {
			return;
		}
		final ModeController modeController = getNodeView().getMap().getModeController();
		final Object renderingHint = modeController.getController().getMapViewManager().setEdgesRenderingHint(g);
		paintBackgound(g);
		paintDragOver(g);
		final Color edgeColor = nodeView.getEdgeColor();
		g.setColor(edgeColor);
		g.setStroke(MainView.DEF_STROKE);
		paintNodeShape(g);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, renderingHint);
		super.paintComponent(g);
	}
	
	abstract protected void paintNodeShape(final Graphics2D g);

}