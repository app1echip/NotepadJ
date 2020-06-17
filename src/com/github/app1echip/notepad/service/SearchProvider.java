package com.github.app1echip.notepad.service;

import java.util.ArrayList;
import java.util.Collections;

public class SearchProvider {
    private static SearchProvider instance = new SearchProvider();

    public static SearchProvider get() {
        return instance;
    }

    public enum DIRECTION {
        UP, DOWN
    }

    private String context = "";
    private String query = "";
    private int start = 0;
    private int nowAt = -1;

    private boolean matchCase = false;
    private boolean wrapAround = false;
    private DIRECTION direction = DIRECTION.DOWN;

    private boolean dirty = true;

    private ArrayList<Integer> indexes = new ArrayList<>();

    public void setClean() {
        dirty = false;
    }

    public void setContext(String context) {
        if (!context.equals(this.context)) {
            this.context = context;
            dirty = true;
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        if (!query.equals(this.query)) {
            this.query = query;
            dirty = true;
        }
    }

    public void setStart(int start) {
        if (start != this.start) {
            this.start = start;
            dirty = true;
        }
    }

    public void setMatchCase(boolean matchCase) {
        if (matchCase != this.matchCase) {
            this.matchCase = matchCase;
            dirty = true;
        }
    }

    public void setWrapAround(boolean wrapAround) {
        if (wrapAround != this.wrapAround) {
            this.wrapAround = wrapAround;
            dirty = true;
        }
    }

    public void setDirection(DIRECTION direction) {
        if (direction != this.direction) {
            this.direction = direction;
            dirty = true;
        }
    }

    public int findNext() {
        if (dirty) {
            updateIndexes();
            dirty = false;
            return indexes.get(nowAt);
        }
        if (indexes.size() == 0)
            return -1;
        if (wrapAround)
            nowAt = (nowAt + 1) % indexes.size();
        else if (nowAt + 1 == indexes.size())
            return -1;
        else
            nowAt++;
        return indexes.get(nowAt);
    }

    public int findPrevious() {
        if (dirty) {
            updateIndexes();
            dirty = false;
            return indexes.get(nowAt);
        }
        if (indexes.size() == 0)
            return -1;
        if (wrapAround)
            nowAt = (nowAt - 1) % indexes.size();
        else if (nowAt == 0)
            return -1;
        else
            nowAt--;
        return indexes.get(nowAt);
    }

    private void updateIndexes() {
        if (!matchCase) {
            context = context.toLowerCase();
            query = query.toLowerCase();
        }
        indexes.clear();
        nowAt = -1;
        int index;
        if (direction == DIRECTION.DOWN) {
            index = context.indexOf(query, start);
            while (index != -1) {
                indexes.add(index);
                index = ++index != context.length() ? context.indexOf(query, index) : -1;
            }
            if (wrapAround) {
                index = context.indexOf(query);
                while (index != -1 && index < start) {
                    indexes.add(index);
                    index = context.indexOf(query, index + 1);
                }
            }
        } else {
            index = context.indexOf(query);
            while (index != -1 && index < start) {
                indexes.add(index);
                index = context.indexOf(query, index + 1);
            }
            Collections.reverse(indexes);
            if (wrapAround) {
                ArrayList<Integer> optional = new ArrayList<>();
                index = context.indexOf(query, start);
                while (index != -1) {
                    optional.add(index);
                    index = ++index != context.length() ? context.indexOf(query, index) : -1;
                }
                Collections.reverse(optional);
                indexes.addAll(optional);
            }
        }
        if (indexes.size() != 0)
            nowAt = 0;
    }
}