package oop.focus.homepage;

import oop.focus.common.Repetition;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyManager;
import oop.focus.homepage.model.HotKeyManagerImpl;
import oop.focus.homepage.model.HotKeyType;
import oop.focus.homepage.model.HotKeyImpl;

import org.joda.time.LocalDateTime;
import org.junit.Test;



public class PopulateEventAndHotKey {

    private final DataSource dsi = new DataSourceImpl();
    private final EventManager event = new EventManagerImpl(dsi);
    private final HotKeyManager hotKey = new HotKeyManagerImpl(dsi, event);

    @Test
    public void populateEvent(){
        final Event repeMonth = new EventImpl("Month", new LocalDateTime(2021, 1, 14, 00, 00), new LocalDateTime(2021, 1, 15, 2, 00), Repetition.MONTHLY);
        final Event repDayly = new EventImpl("Daily", new LocalDateTime(2021, 4,10,8,00), new LocalDateTime(2021, 4, 10, 10 ,00), Repetition.DAILY);
        final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9,26,9,30), new LocalDateTime(2021, 9,26,10,30), Repetition.ONCE);
        final Event second = new EventImpl("Allenamento" , new LocalDateTime(2021, 4, 11, 7,30), new LocalDateTime(2021, 4, 11, 7,30), Repetition.ONCE);
        final Event third =new EventImpl("Gita", new LocalDateTime(2021, 9,26,9,30), new LocalDateTime(2021, 9,27,10,30), Repetition.ONCE);
        final Event fourth = new EventImpl("Bere", new LocalDateTime(2021, 4,10,9,30), new LocalDateTime(2021, 4,10,9,30), Repetition.ONCE);
        final Event fifth = new EventImpl("Bere", new LocalDateTime(2021, 8,10,9,30), new LocalDateTime(2021, 8,10,9,30), Repetition.ONCE);
        final Event sixth = new EventImpl("Giornata", new LocalDateTime(2021, 4, 12, 23, 10), new LocalDateTime(2021, 4, 14, 16, 00), Repetition.ONCE);
        final Event seventh = new EventImpl("normale", new LocalDateTime(2021, 4, 12, 23, 10), new LocalDateTime(2021, 4, 12, 23, 45), Repetition.ONCE);
        final Event eight = new EventImpl("Prova", new LocalDateTime(2021, 4, 7, 12, 00), new LocalDateTime(2021, 4, 7, 13, 00), Repetition.WEEKLY);
        try {
            this.event.addEvent(repeMonth);
        } catch (IllegalStateException ignored) {}
        try {
            this.event.addEvent(repDayly);
        } catch (IllegalStateException ignored) {}
        /*try {
            this.event.addEvent(first);
        } catch (IllegalStateException ignored) {}
        try{
            this.event.addEvent(second);
        } catch (IllegalStateException ignored) {}
        try{
            this.event.addEvent(third);
        } catch (IllegalStateException ignored) {}
        try{
            this.event.addEvent(fourth);
        } catch (IllegalStateException ignored) {}
        try{
            this.event.addEvent(fifth);
        } catch (IllegalStateException ignored) {}
        try{
            this.event.addEvent(sixth);
        } catch (IllegalStateException ignored) {}

        try{
            this.event.addEvent(seventh);
        }catch (IllegalStateException ignored) {}

        try{
            this.event.addEvent(eight);
        } catch (IllegalStateException ignored) {}
*/
    }

    @Test
    public void populateHotKey(){
        final HotKey uno = new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY);
        final HotKey due = new HotKeyImpl("Shopping", HotKeyType.EVENT);
        final HotKey tre = new HotKeyImpl("Bere", HotKeyType.COUNTER);
        final HotKey quattro = new HotKeyImpl("Spesa", HotKeyType.ACTIVITY);
        final HotKey cinque = new HotKeyImpl("Addominali", HotKeyType.COUNTER);

        this.hotKey.add(uno);
        this.hotKey.add(due);
        this.hotKey.add(tre);
        this.hotKey.add(quattro);
        this.hotKey.add(cinque);
    }
}
