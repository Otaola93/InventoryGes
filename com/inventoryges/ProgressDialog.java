package com.inventoryges;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDialog extends JDialog implements ComponentListener, Runnable
{
	private static final int PROGRESS_BAR_WIDTH = 200;
	private Runnable mRunnable;
	private JProgressBar mProgressBar;
	private JLabel mLabelMessage;

	public ProgressDialog(JFrame parent, Runnable runnable, String message)
	{
		super(parent);

		// Setup progress bar...
		mProgressBar = new JProgressBar();
		Dimension preferredSize = mProgressBar.getPreferredSize();
		preferredSize.width = PROGRESS_BAR_WIDTH;
		mProgressBar.setPreferredSize(preferredSize);

		// Setup label...
		mLabelMessage = new JLabel(message);

		// Add content...
		JPanel contentPane = (JPanel)getContentPane();
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = GridBagConstraints.RELATIVE;
		gc.anchor = GridBagConstraints.NORTHWEST;
		contentPane.add(mLabelMessage, gc);
		gc.weightx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(mProgressBar, gc);
		setTitle("");
		setModal(true);
		pack();

		// Setup runnable...
		mRunnable = runnable;

		// Setup event handlers...
		addComponentListener(this);

		// Show...
		this.setVisible(true);
	}

	public void setVisible(boolean visible)
	{
		mProgressBar.setIndeterminate(visible);
		super.setVisible(visible);
	}

	public void run()
	{
		mRunnable.run();
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				setVisible(false);
			}
		});
	}

	public void componentShown(ComponentEvent event)
	{
		new Thread(this).start();
	}

	public void componentHidden(ComponentEvent event)
	{
	}

	public void componentMoved(ComponentEvent event)
	{
	}

	public void componentResized(ComponentEvent event)
	{
	}
}
