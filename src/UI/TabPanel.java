package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabPanel extends JFrame {

	public TabPanel() {
		setTitle("Tool Bar");
		JTabbedPane jtp = new JTabbedPane();

		getContentPane().add(jtp);

		// create tabs
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();

		jtp.addTab("Staff", jp1);
		jtp.addTab("Leave Application", jp2);
		jtp.addTab("Application Endorsement", jp3);

		jtp.addChangeListener(changeListener);

		SetTab1(jp1);
	}

	ChangeListener changeListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			JTabbedPane p = (JTabbedPane) e.getSource();
			int i = p.getSelectedIndex();
			if (i == 0) {
				LoginPanel loginpanel = new LoginPanel("staff");
			} else if (i == 1) {
				LoginPanel loginpanel = new LoginPanel("leave");
			}
			if (i == 2) {
				LoginPanel loginpanel = new LoginPanel("endorse");
			}

		}
	};

	// tab for staff management
	private void SetTab1(JPanel jp) {
		try {
			LoginPanel loginpanel = new LoginPanel("staff");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
