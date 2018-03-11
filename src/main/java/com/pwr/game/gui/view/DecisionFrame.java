package com.pwr.game.gui.view;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zlakomiak226190
 */
public class DecisionFrame extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final String decisionFrameTitle = "Ranking graczy";
    private static final String congratulationsString = "Gratulacje";
    private static final String continueButtonText = "Kontynuuj";
    private static final String rankingResetButtonText = "Resetuj ranking";
    private static final String gameExitButtonText = "Zakończ grę";

    private JPanel decisionFramePanel;

    private JPanel congratulationsPanel;
    private JLabel congratulationsLabel;

    private JPanel buttonsPanel;
    private JButton continueButton;
    private JButton rankingResetButton;
    private JButton gameExitButton;

    private JPanel rankingPanel;
    private JScrollPane rankingTableScrollPane;
    private JTable rankingTable;
    private String[] rankingTableColumnNames = {"Gracz", "Liczba punktów"};
    private Object[][] rankingTableData;

    public JButton getContinueButton() {
        return continueButton;
    }

    public JButton getRankingResetButton() {
        return rankingResetButton;
    }

    public JButton getGameExitButton() {
        return gameExitButton;
    }

    public JPanel getDecisionFramePanel() {
        return decisionFramePanel;
    }


    public DecisionFrame() {
        super(decisionFrameTitle);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setContentPane(decisionFramePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void createButtonsLabels() {
        continueButton.setText(continueButtonText);
        rankingResetButton.setText(rankingResetButtonText);
        gameExitButton.setText(gameExitButtonText);
        buttonsPanel.repaint();
        buttonsPanel.revalidate();
    }

    public void createCongratulationsLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(congratulationsString + ": ");
        int maxPoints = (int) rankingTableData[0][1];
        stringBuilder.append(rankingTableData[0][0]);
        int i = 1;
        while ((i < rankingTableData.length) && ((int) rankingTableData[i][1] == maxPoints)) {
            stringBuilder.append(", ");
            stringBuilder.append(rankingTableData[i][0]);
            i++;
        }
        stringBuilder.append("!!!");
        congratulationsLabel.setText(stringBuilder.toString());
        congratulationsPanel.repaint();
        congratulationsPanel.revalidate();
    }

    public void createRankingTable(Game game) {
        Map<Player, Integer> points = game.getResult();
        rankingTableData = new Object[points.size()][2];
        Map<Player, Integer> sortedPoints = MapUtil.sortByValue(points);
        int i = 0;
        for (Map.Entry<Player, Integer> entry : sortedPoints.entrySet()) {
            rankingTableData[i][0] = entry.getKey().getTurtle();
            rankingTableData[i][1] = entry.getValue();
            i++;
        }
        rankingTable = new JTable(new DefaultTableModel(rankingTableData, rankingTableColumnNames));
        rankingTable.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
        rankingTableScrollPane.setViewportView(rankingTable);
        rankingPanel.repaint();
        rankingPanel.revalidate();
    }
}

class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}

class CustomRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String description = table.getValueAt(row, column).toString();

        if (description == "YELLOW")
            cellComponent.setBackground(Color.YELLOW);
        else if (description == "BLUE")
            cellComponent.setBackground(new Color(90, 90, 255));
        else if (description == "RED")
            cellComponent.setBackground(Color.RED);
        else if (description == "GREEN")
            cellComponent.setBackground(Color.GREEN);
        else if (description == "PURPLE")
            cellComponent.setBackground(new Color(150, 50, 255));

        return cellComponent;
    }
}
