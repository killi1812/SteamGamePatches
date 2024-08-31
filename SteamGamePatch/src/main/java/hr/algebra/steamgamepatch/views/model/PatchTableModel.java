/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.steamgamepatch.views.model;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Patch;
import hr.algebra.utilities.MessageUtils;
import java.sql.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fran
 */
public class PatchTableModel extends AbstractTableModel {

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 3:
                return Date.class;
            default:
                return String.class;
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    private static final String[] COLUMN_NAMES = {
        "Id",
        "Title",
        "Link",
        "Published",
        "Author"
    };

    private final List<Patch> patches;
    private final Repository repo;

    public PatchTableModel(List<Patch> patches) {
        this.patches = patches;
        repo = RepositoryFactory.getInstance();
    }

    @Override
    public int getRowCount() {
        return patches.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return patches.get(rowIndex).idPatch;
            case 1:
                return patches.get(rowIndex).title;
            case 2:
                return patches.get(rowIndex).link;
            case 3:
                return patches.get(rowIndex).pubDate;
            case 4:
                try {
                    return repo.getAuthor(patches.get(rowIndex).authorId);
                } catch (Exception e) {
                    System.out.println(e);
                    MessageUtils.showErrorMessage("ERROR", "Faild to get Author");
                    return "";
                }
            default:
                throw new AssertionError();
        }
    }

}
