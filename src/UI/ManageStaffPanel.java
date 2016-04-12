package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Domain.GeneralStaff;
import Domain.Staff;
import Domain.Supervisor;

public class ManageStaffPanel extends JFrame {

	JButton b1;
	JComboBox<String> comboStaff;

	JTextField txtStaff;
	JTable tblStaff;
	String loginStaff;

	private String RoleValue;
	private String SelStaff;

	public ManageStaffPanel() {
		try {

			this.setSize(1024, 768);
			this.setLocation(100, 100);

			LayoutManager flayout = new FlowLayout();
			JPanel pnlStaffCtTop = new JPanel(new BorderLayout());

			JPanel pnlStaffCtTopInner1 = new JPanel(flayout);
			JPanel pnlStaffCtTopInner2 = new JPanel(flayout);
			pnlStaffCtTopInner2.setBackground(Color.GRAY);

			LayoutManager blayout = new BorderLayout();
			JPanel pnlStaffCt = new JPanel(blayout);

			JLabel label1 = new JLabel();
			label1.setText("Staff Management");

			pnlStaffCt.add(label1, BorderLayout.NORTH);

			JLabel lblStaff = new JLabel();
			lblStaff.setText("Staff Name:");

			txtStaff = new JTextField("", 20);

			txtStaff.setSize(1200, 170);
			txtStaff.setVisible(true);

			JLabel lblSupervisor = new JLabel();
			lblSupervisor.setText("Supervisor:");

			comboStaff = new JComboBox<String>();

			updateComboStaff(comboStaff);

			pnlStaffCtTopInner1.add(lblStaff);
			pnlStaffCtTopInner1.add(txtStaff);
			pnlStaffCtTopInner1.add(lblSupervisor);
			pnlStaffCtTopInner1.add(comboStaff);

			/** Create the radio buttons.*/
			JRadioButton radNormal = new JRadioButton("Normal");
			radNormal.setMnemonic(KeyEvent.VK_B);
			radNormal.setActionCommand("Normal");
			radNormal.setSelected(true);

			JRadioButton radSupervisor = new JRadioButton("Supervisor");
			radSupervisor.setMnemonic(KeyEvent.VK_B);
			radSupervisor.setActionCommand("Supervisor");
			radSupervisor.setSelected(true);

			JRadioButton radDirector = new JRadioButton("Director");
			radDirector.setMnemonic(KeyEvent.VK_C);
			radDirector.setActionCommand("Director");

			/** Group the radio buttons.*/
			ButtonGroup radGroup = new ButtonGroup();
			radGroup.add(radNormal);
			radGroup.add(radSupervisor);
			radGroup.add(radDirector);

			/** Register a listener for the radio buttons.*/
			radNormal.addActionListener(new RadAction());
			radDirector.addActionListener(new RadAction());

			pnlStaffCtTopInner1.add(radNormal);
			pnlStaffCtTopInner1.add(radDirector);

			/** create button*/
			JButton btnAdd = new JButton("Add New Staff");

			JButton btnDelete = new JButton("Delete Staff");

			/** Listen for actions on buttons.*/
			btnAdd.addActionListener(new ActionAdd());
			btnDelete.addActionListener(new ActionDelete());

			btnAdd.setToolTipText("Click this button to add new Staff.");
			pnlStaffCtTopInner2.add(btnAdd);

			btnAdd.setToolTipText("Click this button to delete Staff.");
			pnlStaffCtTopInner2.add(btnDelete);

			JButton btnUpdate = new JButton("Update Staff");

			/** Listen for actions on buttons.*/
			btnUpdate.addActionListener(new ActionUpdate());

			btnUpdate.setToolTipText("Click this button to update Staff.");
			pnlStaffCtTopInner2.add(btnUpdate);

			pnlStaffCt.add(pnlStaffCtTop, BorderLayout.CENTER);
			pnlStaffCtTop.add(pnlStaffCtTopInner1, BorderLayout.NORTH);
			pnlStaffCtTop.add(pnlStaffCtTopInner2, BorderLayout.CENTER);
			/** add the table grid*/
			tblStaff = new JTable();

			JScrollPane scrollPane = new JScrollPane(tblStaff);
			setUpTableData(tblStaff);

			pnlStaffCt.add(scrollPane, BorderLayout.SOUTH);

			this.add(pnlStaffCt, BorderLayout.CENTER);
			this.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void setUpTableData(JTable jTable) {
		DefaultTableModel tableModel = new DefaultTableModel(0, 0);

		String header[] = new String[] { "Staff Name", "Supervisor", "Role" };

		/** add header in table model*/
		tableModel.setColumnIdentifiers(header);
		/** set model into the table object*/
		jTable.setModel(tableModel);

		tableModel.setRowCount(0);

		jTable.setRowSelectionAllowed(true);
		ListSelectionModel rowSelectionModel = jTable.getSelectionModel();
		rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		/**
		 * add the row select listener
		 */
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				int[] selectedRow = jTable.getSelectedRows();

				for (int i = 0; i < selectedRow.length; i++) {
					SelStaff = (String) jTable.getValueAt(selectedRow[i], 0);
					txtStaff.setText(SelStaff);
					break;
				}

				System.out.println("Selected: " + SelStaff);
			}
		});

		/**
		 * update the table
		 */
		ArrayList<Staff> staffList = new ArrayList<Staff>();

		staffList = Domain.Main.staffsData.getAllStaff();

		if (staffList.size() > 0) {
			for (int i = 0; i < staffList.size(); i++) {
				String[] data = new String[3];

				data[0] = staffList.get(i).getName();
				data[1] = staffList.get(i).getSupervisor();
				data[2] = staffList.get(i).getRole();

				tableModel.addRow(data);
			}
		}

		tableModel.fireTableDataChanged();
	}

	private void updateComboStaff(JComboBox<String> comboList) {
		comboList.removeAllItems();
		comboList.addItem("Pls select staff");
		ArrayList<Staff> staffList = new ArrayList<Staff>();

		staffList = Domain.Main.staffsData.getAllStaff();

		if (staffList.size() > 0) {
			for (int i = 0; i < staffList.size(); i++) {
				comboList.addItem(staffList.get(i).getName());
			}
		}

		comboList.setSelectedIndex(0);
	}

	/**
	 * actions
	 */
	private class ActionAdd implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String tempSupervisor = "";

			Staff newStaff = new GeneralStaff();

			newStaff.setName(txtStaff.getText());
			newStaff.setRole(RoleValue);
			if (comboStaff.getSelectedIndex() > 0)
				tempSupervisor = comboStaff.getSelectedItem().toString();

			newStaff.setSupervisor(tempSupervisor);

			/**
			 * only one director
			 */
			if (RoleValue == "Director") {
				if (Domain.Main.staffsData.hasDirector()) {
					JOptionPane.showMessageDialog(null, "Only one director allowed.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (newStaff.validStaffSize()) {
						if (newStaff.validHasSupervisor()) {
							Domain.Main.staffsData.addStaffRecord(newStaff);
						} else {
							JOptionPane.showMessageDialog(null, "You have not set supervisor!", "Error",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Staff Name length must more than 3 character!", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} else {
				/**
				 * validate staff, only director allow no supervisor
				 */
				if (newStaff.validStaffSize()) {
					if (newStaff.validHasSupervisor()) {
						Domain.Main.staffsData.addStaffRecord(newStaff);
						if (!Domain.Main.staffsData.getRole(tempSupervisor).equals("Director"))
							Domain.Main.staffsData.setRole(tempSupervisor, "Supervisor");
					} else {
						JOptionPane.showMessageDialog(null, "You have not set supervisor!", "Error!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Staff Name length must more than 3 character!", "Error!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			/** update the supervisor list*/
			updateComboStaff(comboStaff);

			/** update the grid*/
			setUpTableData(tblStaff);
		}
	}

	/** delete*/
	private class ActionDelete implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JOptionPane.showMessageDialog(null, "Confirm to delete staff, " + SelStaff, "Delete?",
					JOptionPane.INFORMATION_MESSAGE);

			Domain.Main.staffsData.deleteStaff(SelStaff);

			/** update the supervisor list*/
			updateComboStaff(comboStaff);

			/** update the grid*/
			setUpTableData(tblStaff);
		}
	}

	/** update*/
	private class ActionUpdate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String tempSupervisor = "";
			if (comboStaff.getSelectedIndex() > 0)
				tempSupervisor = comboStaff.getSelectedItem().toString();

			Domain.Main.staffsData.setSupervisor(SelStaff, tempSupervisor);
			Domain.Main.staffsData.setRole(SelStaff, RoleValue);
			Domain.Main.staffsData.setName(SelStaff, txtStaff.getText());

			Domain.Main.staffsData.setRole(tempSupervisor, "Supervisor");
			// update the grid
			setUpTableData(tblStaff);
		}
	}

	/** Listens to the radio buttons. */
	private class RadAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RoleValue = e.getActionCommand();
		}
	}
}
