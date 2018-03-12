package com.pwr.game.gui.view;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author zlakomiak226190
 */
public class DecisionFrame extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private static final String DECISION_FRAME_TITLE = "Ranking graczy";
    private static final String CONGRATULATIONS_STRING = "Gratulacje wygrania ostatniej rundy";
    private static final String CONTINUE_BUTTON_TEXT = "Kontynuuj";
    private static final String RANKING_RESET_BUTTON_TEXT = "Resetuj ranking";
    private static final String GAME_EXIT_BUTTON_TEXT = "Zakończ grę";
    private static final String[] RANKING_TABLE_COLUMN_NAMES = {"Gracz", "Kolor", "Liczba punktów"};

    private JPanel decisionFramePanel;

    private JPanel congratulationsPanel;
    private JLabel congratulationsLabel;

    private JPanel buttonsPanel;
    private JButton continueButton;
    private JButton rankingResetButton;
    private JButton gameExitButton;

    private JPanel rankingPanel;
    private JScrollPane rankingTableScrollPane;

    public JButton getContinueButton() {
        return continueButton;
    }

    public JButton getRankingResetButton() {
        return rankingResetButton;
    }

    public JButton getGameExitButton() {
        return gameExitButton;
    }


    public DecisionFrame() {
        super(DECISION_FRAME_TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setContentPane(decisionFramePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void createButtonsLabels() {
        continueButton.setText(CONTINUE_BUTTON_TEXT);
        rankingResetButton.setText(RANKING_RESET_BUTTON_TEXT);
        gameExitButton.setText(GAME_EXIT_BUTTON_TEXT);
        buttonsPanel.repaint();
        buttonsPanel.revalidate();
    }

    public void createCongratulationsLabel(Player lastWinner) {
        String stringBuilder = (CONGRATULATIONS_STRING + ", ") +
                lastWinner.getName() +
                "!!!";
        congratulationsLabel.setText(stringBuilder);
        congratulationsPanel.repaint();
        congratulationsPanel.revalidate();
    }

    public void createRankingTable(Game game) {
        Map<Player, Integer> points = game.getResult();
        Object[][] rankingTableData = new Object[points.size()][3];
        Map<Player, Integer> sortedPoints = MapUtil.sortByValue(points);
        int i = 0;
        for (Map.Entry<Player, Integer> entry : sortedPoints.entrySet()) {
            rankingTableData[i][0] = entry.getKey().getName();
            rankingTableData[i][1] = entry.getKey().getTurtle();
            rankingTableData[i][2] = entry.getValue();
            i++;
        }
        JTable rankingTable = new JTable(new DefaultTableModel(rankingTableData, RANKING_TABLE_COLUMN_NAMES));
        rankingTable.getColumnModel().getColumn(1).setCellRenderer(new CustomRenderer());
        rankingTableScrollPane.setViewportView(rankingTable);
        rankingPanel.repaint();
        rankingPanel.revalidate();
    }
}

class MapUtil {

    private MapUtil() {
        // util
    }

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

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String description = table.getValueAt(row, column).toString();

        if ("YELLOW".equals(description)) {
            cellComponent.setBackground(new Color(249, 255, 195));
            cellComponent.setForeground(new Color(249, 255, 195));

        } else if ("BLUE".equals(description)) {
            cellComponent.setBackground(new Color(186, 209, 255));
            cellComponent.setForeground(new Color(186, 209, 255));

        } else if ("RED".equals(description)) {
            cellComponent.setBackground(new Color(255, 201, 189));
            cellComponent.setForeground(new Color(255, 201, 189));

        } else if ("GREEN".equals(description)) {
            cellComponent.setBackground(new Color(187, 255, 189));
            cellComponent.setForeground(new Color(187, 255, 189));

        } else if ("PURPLE".equals(description)) {
            cellComponent.setBackground(new Color(239, 193, 255));
            cellComponent.setForeground(new Color(239, 193, 255));
        }

        return cellComponent;
    }
}
