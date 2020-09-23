import models.Staff;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserInterface {

    Random rnd = new Random();

    private Dimension dimensionFrame;
    private Dimension dimensionTable;
    private Dimension dimensionScrollTable;
    private StaffTableModel staffTableModel;

    public UserInterface(Dimension dimensionFrame, Dimension dimensionTable, Dimension dimensionScrollTable) {
        this.dimensionFrame = dimensionFrame;
        this.dimensionTable = dimensionTable;
        this.dimensionScrollTable = dimensionScrollTable;
    }

    public void createUI(){

        MigLayout layout = new MigLayout(
                "", // Layout Constraints
                "[center]20[center]20[center]20[center]", // Column constraints
                "[center]"); // Row constraints
        Font font = new Font("Calibri", Font.BOLD, 20);

        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(new Dimension(dimensionFrame));
        frame.setMinimumSize(new Dimension(dimensionFrame));
        frame.setMaximumSize(new Dimension(dimensionFrame));
        frame.setPreferredSize(new Dimension(dimensionFrame));
        frame.setLayout(layout);

        JPanel panel = new JPanel();
        panel.setLayout(layout);

        List<Staff> listStaff = new ArrayList<>();

        // создание начальных данных для таблицы
        for (int i = 0; i < 100; i++) {
            listStaff.add(new Staff(i,
                    "Имя " + i,
                    "Фамилия " + i,
                    "Менеджер " + i,
                    String.valueOf(rnd.nextInt(9)) + String.valueOf(rnd.nextInt(9)) + String.valueOf(rnd.nextInt(9)) + "-"
                            + String.valueOf(rnd.nextInt(9)) + String.valueOf(rnd.nextInt(9)) + "-"
                            + String.valueOf(rnd.nextInt(9)) + String.valueOf(rnd.nextInt(9)),
                    rnd.nextInt(20) + 30));
        }

        StaffTableModel staffTableModel = new StaffTableModel(listStaff);
        JTable table = new JTable(staffTableModel);
        table.setSize(new Dimension(dimensionTable));
        table.setMinimumSize(new Dimension(dimensionTable));
        table.setMaximumSize(new Dimension(dimensionTable));
        table.setRowHeight(25);

        JScrollPane jscrlp = new JScrollPane(table);
        jscrlp.setViewportView(table);
        table.setPreferredScrollableViewportSize(new Dimension(dimensionScrollTable));

        JButton buttonCreate = new JButton("CREATE");
        buttonCreate.setFont(font);
        buttonCreate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){

                String inputName = "";
                String inputSurname = "";
                String inputPosition = "";
                String inputPhoneNumber = "";
                String inputAgeString = "";
                int inputAge = 0;

                // ввод/созднаие поля Name, через Input Dialog, с кнопками "OK", "Cancel"
                // обработка нажатия кнопки "OK"
                inputName = JOptionPane.showInputDialog(
                        null,
                        "Введите поле Name");

                // обработка нажатия кнопки "Cancel"
                if(inputName == null || (inputName != null && ("".equals(inputName)))) {
                    return;
                }

                // подтверждение ввода, через ConfirmDialog, с кнопками "Yes" / "No"
                int result = JOptionPane.showConfirmDialog(null,
                        inputName,"Вы ввели",
                        JOptionPane.YES_NO_OPTION);

                // Обработка нажатия кнопки "Yes" (кнопка "No" реализована в блоке else, через оператор return)
                if(result == JOptionPane.YES_OPTION){
                    inputSurname = JOptionPane.showInputDialog(
                            null,
                            "Введите поле Surname");

                    if(inputSurname == null || (inputSurname != null && ("".equals(inputSurname)))) {
                        return;
                    }

                    result = JOptionPane.showConfirmDialog(null,
                            inputSurname, "Вы ввели",
                            JOptionPane.YES_NO_OPTION);

                    if(result == JOptionPane.YES_OPTION){
                        inputPosition = JOptionPane.showInputDialog(
                                null,
                                "Введите поле Position");

                        if(inputPosition == null || (inputPosition != null && ("".equals(inputPosition)))) {
                            return;
                        }

                        result = JOptionPane.showConfirmDialog(null,
                                inputPosition, "Вы ввели",
                                JOptionPane.YES_NO_OPTION);

                        if(result == JOptionPane.YES_OPTION){
                            inputPhoneNumber = JOptionPane.showInputDialog(
                                    null,
                                    "Введите поле PhoneNumber");

                            if(inputPhoneNumber == null || (inputPhoneNumber != null && ("".equals(inputPhoneNumber)))) {
                                return;
                            }

                            result = JOptionPane.showConfirmDialog(null,
                                    inputPhoneNumber, "Вы ввели",
                                    JOptionPane.YES_NO_OPTION);

                            if(result == JOptionPane.YES_OPTION){
                                inputAgeString = JOptionPane.showInputDialog(
                                        null,
                                        "Введите поле Age");

                                if(inputAgeString == null || (inputAgeString != null && ("".equals(inputAgeString)))) {
                                    return;
                                }

                                try{
                                    inputAge = Integer.parseInt(inputAgeString);
                                    result = JOptionPane.showConfirmDialog(null,
                                            inputAge,"Вы ввели",
                                            JOptionPane.YES_NO_OPTION);

                                    if(result == JOptionPane.YES_OPTION){
                                        Staff tempStaff = new Staff(listStaff.size(), inputName, inputSurname, inputPosition, inputPhoneNumber, inputAge);

                                        staffTableModel.add(tempStaff);

                                        table.revalidate();
                                        table.repaint();
                                    }

                                    else{
                                        return;
                                    }

                                }catch(Exception ex){
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(null,
                                            "Некорректный формат данных");
                                }
                            }

                            else{
                                return;
                            }
                        }

                        else{
                            return;
                        }
                    }

                    else{
                        return;
                    }
                }

                else{
                    return;
                }
            }
        });

        JButton buttonReadByIndex = new JButton("READ BY INDEX");
        buttonReadByIndex.setFont(font);
        buttonReadByIndex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = JOptionPane.showInputDialog(
                        "Введите ID для поиска");

                if(inputString == null || (inputString != null && ("".equals(inputString)))) {
                    return;
                }

                try{
                    int inputId = Integer.parseInt(inputString);
                    Staff temp = new Staff();

                    for (int i = 0; i < listStaff.size(); i++) {
                        temp = listStaff.get(i);

                        if (inputId == temp.getId()){
                            int result = JOptionPane.showConfirmDialog(null,
                                    "Вы ввели ID = " + inputId,
                                    "Окно подтверждения",
                                    JOptionPane.YES_NO_CANCEL_OPTION);

                            if(result == JOptionPane.YES_OPTION){

                                staffTableModel.get(inputId);

                                table.revalidate();
                                table.repaint();

                                return;

                            }else if (result == JOptionPane.NO_OPTION){
                                JOptionPane.showMessageDialog(null,
                                        "Повторите ввод индекса");
                                return;

                            }else if (result == JOptionPane.CANCEL_OPTION) {
                                JOptionPane.showMessageDialog(null,
                                        "Поиск по индексу отменён");
                                return;
                            }
                        }
                    }

                    JOptionPane.showMessageDialog(null,
                            "Индекс " + inputId + " отсутствует в таблице");

                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Некорректный формат данных");
                }
            }
        });

        JButton buttonUpdate = new JButton("UPDATE");
        buttonUpdate.setFont(font);
        buttonUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){

                int inputId = 0;

                // проверка, что введённый Id находится в пределах данных, которые представлены в таблице
                String inputIdString = JOptionPane.showInputDialog(
                        null,
                        "Введите поле Id записи, которая будет изменена");

                if(inputIdString == null || (inputIdString != null && ("".equals(inputIdString)))) {
                    return;
                }

                int result = JOptionPane.showConfirmDialog(null,
                        inputIdString,"Вы ввели",
                        JOptionPane.YES_NO_OPTION);

                if(result == JOptionPane.YES_OPTION){
                    try{
                        inputId = Integer.parseInt(inputIdString);
                        if(inputId <= 0 || inputId >= listStaff.size()){
                            JOptionPane.showMessageDialog(null,
                                    "Данная запись отсутствует");
                            return;
                        }

                        staffTableModel.get(inputId);

                        table.revalidate();
                        table.repaint();

                    }catch(Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,
                                "Некорректный формат данных");
                        return;
                    }
                }
                else{
                    return;
                }

                String inputNameNew = "";
                String inputSurnameNew = "";
                String inputPositionNew = "";
                String inputPhoneNumberNew = "";
                String inputAgeStringNew = "";
                int inputAgeNew = 0;

                inputNameNew = JOptionPane.showInputDialog(
                        null,
                        "Введите новое значение для поля Name");

                if(inputNameNew == null || (inputNameNew != null && ("".equals(inputNameNew)))) {
                    return;
                }

                result = JOptionPane.showConfirmDialog(null,
                        inputNameNew,"Вы ввели",
                        JOptionPane.YES_NO_OPTION);

                if(result == JOptionPane.YES_OPTION){
                    inputSurnameNew = JOptionPane.showInputDialog(
                            null,
                            "Введите новое значение для поля Surname");

                    if(inputSurnameNew == null || (inputSurnameNew != null && ("".equals(inputSurnameNew)))) {
                        return;
                    }

                    result = JOptionPane.showConfirmDialog(null,
                            inputSurnameNew, "Вы ввели",
                            JOptionPane.YES_NO_OPTION);

                    if(result == JOptionPane.YES_OPTION){
                        inputPositionNew = JOptionPane.showInputDialog(
                                null,
                                "Введите новое значение для поля Position");

                        if(inputPositionNew == null || (inputPositionNew != null && ("".equals(inputPositionNew)))) {
                            return;
                        }

                        result = JOptionPane.showConfirmDialog(null,
                                inputPositionNew, "Вы ввели",
                                JOptionPane.YES_NO_OPTION);

                        if(result == JOptionPane.YES_OPTION){
                            inputPhoneNumberNew = JOptionPane.showInputDialog(
                                    null,
                                    "Введите новое значение для поля PhoneNumber");

                            if(inputPhoneNumberNew == null || (inputPhoneNumberNew != null && ("".equals(inputPhoneNumberNew)))) {
                                return;
                            }

                            result = JOptionPane.showConfirmDialog(null,
                                    inputPhoneNumberNew, "Вы ввели",
                                    JOptionPane.YES_NO_OPTION);

                            if(result == JOptionPane.YES_OPTION){
                                inputAgeStringNew = JOptionPane.showInputDialog(
                                        null,
                                        "Введите новое значение для поля Age");

                                if(inputAgeStringNew == null || (inputAgeStringNew != null && ("".equals(inputAgeStringNew)))) {
                                    return;
                                }

                                try{
                                    inputAgeNew = Integer.parseInt(inputAgeStringNew);
                                    result = JOptionPane.showConfirmDialog(null,
                                            inputAgeNew,"Вы ввели",
                                            JOptionPane.YES_NO_OPTION);

                                    if(result == JOptionPane.YES_OPTION){

                                        Staff newStaff = new Staff(inputId, inputNameNew, inputSurnameNew, inputPositionNew, inputPhoneNumberNew, inputAgeNew);

                                        staffTableModel.update(inputId, newStaff);

                                        table.revalidate();
                                        table.repaint();
                                    }

                                    else{
                                        return;
                                    }

                                }catch(Exception ex){
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(null,
                                            "Некорректный ввод");
                                }
                            }

                            else{
                                return;
                            }
                        }

                        else{
                            return;
                        }
                    }

                    else{
                        return;
                    }
                }

                else{
                    return;
                }

            }
        });

        JButton buttonDelete = new JButton("DELETE");
        buttonDelete.setFont(font);
        buttonDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                int index = table.getSelectedRow();
                staffTableModel.remove(index);
                table.revalidate();
                table.repaint();
            }
        });

        panel.add(buttonCreate, "cell 0 1, w 180!, h 40!");
        panel.add(buttonReadByIndex, "cell 1 1, w 180!, h 40!");
        panel.add(buttonUpdate, "cell 2 1, w 180!, h 40!");
        panel.add(buttonDelete, "cell 3 1, w 180!, h 40!");

        frame.add(jscrlp, "align center, wrap");
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
