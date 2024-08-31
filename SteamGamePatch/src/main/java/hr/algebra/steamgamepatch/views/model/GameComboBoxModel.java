/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.steamgamepatch.views.model;

import hr.algebra.model.Game;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author fran
 */
public class GameComboBoxModel implements ComboBoxModel<Game> {

    private List<Game> games;
    private Game selectedGame;
    private List<ListDataListener> l;

    public GameComboBoxModel(List<Game> games) {
        this.games = new ArrayList<>();
        this.games.add(new Game(0, "", "", ""));
        this.games.addAll(games);
        selectedGame = null;
        l = new ArrayList<>();
    }

    @Override

    public void setSelectedItem(Object o) {
        selectedGame = (Game) o;
    }

    @Override
    public Object getSelectedItem() {
        return selectedGame;
    }

    @Override
    public int getSize() {
        return games.size();
    }

    @Override
    public Game getElementAt(int i) {
        return games.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener ll) {
        l.add(ll);
    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        l.remove(ll);
    }

}
