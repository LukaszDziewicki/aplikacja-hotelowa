package com.company.MainApp;

import com.company.Const.DateHelper;
import com.company.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class SelectDate extends JFrame
{
    private JLabel CheckDate; JButton submit;
    private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private Properties properties = new Properties();


    public SelectDate()
    {
        model = new UtilDateModel();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        JPanel panel = new JPanel();
        CheckDate = new JLabel("Date:");
        submit = new JButton("Submit");
        panel.add(CheckDate);
        panel.add(datePicker);
        panel.add(submit);
        add(panel);
        setBounds(200,150,300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(submit == e.getSource())
                {
                    Date selectedDate = (Date) datePicker.getModel().getValue();
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String reportDate = df.format(selectedDate);
                    DateHelper.date = reportDate;
                    dispose();
                }
            }
        });


    }



}