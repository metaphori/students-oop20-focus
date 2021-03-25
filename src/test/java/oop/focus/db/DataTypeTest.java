package oop.focus.db;

import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.diary.model.DailyMood;
import oop.focus.diary.model.DailyMoodImpl;
import oop.focus.diary.model.ToDoAction;
import oop.focus.diary.model.ToDoActionImpl;
import oop.focus.fidelitycard.FidelityCard;
import oop.focus.fidelitycard.FidelityCardImpl;
import oop.focus.fidelitycard.FidelityCardType;
import oop.focus.finance.*;
import oop.focus.homepage.model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class DataTypeTest {

    private final DataSource df;

    public DataTypeTest(){
        this.df = new DataSourceImpl();
    }

    @Test
    public void testPerson() {
        var relationships = df.getRelationships();
        var persons = df.getPersons();
        var all = persons.getAll();
        int initialSize = all.size();

        try {
            var rel = "relation1";
            var p1 = new PersonImpl("person1", "relation1");
            var p2 = new PersonImpl("person2", "relation1");
            relationships.save(rel);
            persons.save(p1);
            assertEquals(initialSize+1,all.size());
            persons.save(p2);
            assertEquals(initialSize+2,all.size());
            persons.delete(p1);
            assertEquals(initialSize+1,all.size());
            persons.delete(p2);
            assertEquals(initialSize,all.size());
            relationships.delete(rel);
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }
        try {
            persons.delete(new PersonImpl("Person1", "rel1"));
            fail();
        } catch (Exception e) {
            // success
        }
    }

    @Test
    public void testColors() {
        var colors = df.getColors();
        var all = colors.getAll();
        int initialSize = all.size();
        try {
            var c1 = "color1";
            var c2 = "color2";
            colors.save(c1);
            assertEquals(initialSize+1, all.size());
            colors.save(c2);
            assertEquals(initialSize+2, all.size());
            colors.delete(c1);
            assertEquals(initialSize+1, all.size());
            colors.delete(c2);
            assertEquals(initialSize, all.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            colors.save("ffffffffff"); // invalid color (too long)
            fail();
        } catch (Exception e) {
            // success
        }
    }

    @Test
    public void testCategories() {
        var categories = df.getCategories();
        var colors = df.getColors();
        var all = categories.getAll();
        int initialSize = all.size();
        String c1 = "color1", c2 = "color2";
        try {
            colors.save(c2);
            colors.save(c1);
            List<Category> vars = List.of(new CategoryImpl("cat1 ", c1),
                    new CategoryImpl("cat2", c1),
                    new CategoryImpl("cat3", c1));
            for (var v : vars) {
                categories.save(v);
            }
            assertEquals(initialSize+3, all.size());
            for (var ac : vars) {
                categories.delete(ac);
            }
            colors.delete(c1);
            colors.delete(c2);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            categories.delete(new CategoryImpl("CategoryNotExisting",c1));
            fail();
        } catch (Exception e) {
            //
        }
    }

    @Test
    public void testAccounts() {
        Dao<Account> accounts = df.getAccounts();
        Dao<String> colors = df.getColors();
        var all = accounts.getAll();
        int initialSize = all.size();

        String c1 = "color1", c2 = "color2";
        try {
            colors.save(c2);
            colors.save(c1);
            Account account = new AccountImpl("Account1", c1, 100);
            Account account2 = new AccountImpl("Account2", c1, 100);
            Account account3 = new AccountImpl("Account3", c2, 100);
            accounts.save(account);
            accounts.save(account2);
            accounts.save(account3);
            assertEquals(initialSize + 3, all.size());
            accounts.delete(account);
            accounts.delete(account2);
            accounts.delete(account3);
            assertEquals(initialSize, all.size());
            account = new AccountImpl("Account1", c1, 300);
            account2 = new AccountImpl("Account1", c1, 150);
            assertEquals(account, account2);
            accounts.save(account);
            assertEquals(initialSize + 1, all.size());
            accounts.update(account2);
            assertEquals(initialSize + 1, all.size());
            assertEquals(150, all.get(all.indexOf(account)).getInitialAmount());
            assertEquals(initialSize + 1, all.size());
            accounts.delete(account);
            assertEquals(initialSize, all.size());
            colors.delete(c1);
            colors.delete(c2);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testTransactions() {
        var transactions = df.getTransactions();
        var cats = df.getCategories();
        var colors = df.getColors();
        var accounts = df.getAccounts();
        var all = transactions.getAll();
        int initialSize = all.size();
        var c1 = "color1";
        var ac1 = new AccountImpl("Account 1", c1, 150_000);
        var ac2 = new AccountImpl("Account 2", c1, 10_000);
        var cat1 = new CategoryImpl("cat1", c1);
        var cat2 = new CategoryImpl("cat2", c1);
        try {

            colors.save(c1);
            accounts.save(ac1);
            accounts.save(ac2);
            cats.save(cat1);
            cats.save(cat2);

            List<Transaction> vars = List.of(
                    new TransactionImpl("Transaction1",
                            cat1, new LocalDateTime(2020, 1, 1,2,2,2),
                            ac1, -250, Repetition.ONCE),
                    new TransactionImpl("Transaction2",
                            cat2, new LocalDateTime(2020, 1, 1,2,2,2),
                            ac2, 300, Repetition.ONCE));
            for (var v : vars) {
                transactions.save(v);
            }
            assertEquals(initialSize + 2, all.size());
            for (var v : vars) {
                transactions.delete(v);
            }
            accounts.delete(ac1);
            accounts.delete(ac2);
            cats.delete(cat1);
            cats.delete(cat2);
            colors.delete(c1);
            assertEquals(initialSize, all.size());


        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvents() {
        var events = df.getEvents();
        var rel = df.getRelationships();
        var per = df.getPersons();
        var all = events.getAll();
        int initialSize = all.size();
        try {
            assertEquals(new EventImpl("Event1", LocalDateTime.now(), LocalDateTime.now().plusDays(7), Repetition.BIMONTHLY),
                    new EventImpl("Event1", LocalDateTime.now(), LocalDateTime.now().plusDays(7), Repetition.HALF_YEARLY));
            List<Event> vars = List.of(
                    new EventImpl("Event1", LocalDateTime.now(), LocalDateTime.now().plusDays(5), Repetition.BIMONTHLY),
                    new EventImpl("Event2", LocalDateTime.now(), LocalDateTime.now().plusDays(4), Repetition.HALF_YEARLY),
                    new EventImpl("Event3 ", LocalDateTime.now(), LocalDateTime.now().plusDays(3), Repetition.MONTHLY));
            for (var v : vars) {
                events.save(v);
            }
            assertEquals(initialSize + 3, all.size());
            for (var ac : vars) {
                events.delete(ac);
            }
            var p = new EventImpl("Event1", LocalDateTime.now(), LocalDateTime.now().plusDays(7), Repetition.BIMONTHLY);
            var relation = "figlio";
            events.save(p);
            rel.save(relation);
            var p1 = new PersonImpl("person1", relation);
            var p2 = new PersonImpl("person2", relation);
            var p3 = new PersonImpl("person3", relation);
            per.save(p1);
            per.save(p2);
            per.save(p3);
            p.addPerson(p1);
            p.addPerson(p2);
            events.update(p);

            assertEquals(2, all.get(all.indexOf(p)).getPersons().size());
            p.addPerson(p3);
            events.update(p);
            assertEquals(3, all.get(all.indexOf(p)).getPersons().size());
            p = new EventImpl("Event1", LocalDateTime.now(), LocalDateTime.now().plusDays(7),
                    Repetition.BIMONTHLY, List.of(p1));
            events.update(p);
            assertEquals(1, all.get(all.indexOf(p)).getPersons().size());
            assertEquals(p1, all.get(all.indexOf(p)).getPersons().get(0));
            events.delete(p);
            per.delete(p1);
            per.delete(p2);
            per.delete(p3);
            rel.delete(relation);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        var ev =new EventImpl("Event1", LocalDateTime.now(), LocalDateTime.now().plusDays(5),
                Repetition.BIMONTHLY);
        try {
            events.save(ev);
            events.save(new EventImpl("Event1", LocalDateTime.now(), LocalDateTime.now().plusDays(7), Repetition.HALF_YEARLY));
            assertEquals(initialSize + 1, all.size());
            assertEquals(Repetition.BIMONTHLY, all.get(all.indexOf(ev)).getRipetition());
            events.update(new EventImpl("Event1", LocalDateTime.now(), LocalDateTime.now().plusDays(8), Repetition.HALF_YEARLY));
            assertEquals(1, events.getAll().size());
            assertEquals(Repetition.HALF_YEARLY, all.get(all.indexOf(ev)).getRipetition());
            events.delete(new EventImpl("NotExistingEvent", LocalDateTime.now(), LocalDateTime.now().plusDays(8), Repetition.HALF_YEARLY));
            fail();
        } catch (IllegalArgumentException e) {
            // success
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }
        try {
            events.delete(ev);
            assertEquals(initialSize, all.size());
        } catch (DaoAccessException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDailyMood() {
        Dao<DailyMood> dailyMoods = df.getDailyMoods();
        var all = dailyMoods.getAll();
        int initialSize = all.size();

        try {
            assertEquals(new DailyMoodImpl(5, LocalDate.now().plusDays(5)),
                    new DailyMoodImpl(3, LocalDate.now().plusDays(5)));
            var vars = List.of(new DailyMoodImpl(5, LocalDate.now().plusDays(5)),
                    new DailyMoodImpl(4, LocalDate.now().plusDays(4)),
                    new DailyMoodImpl(3, LocalDate.now().plusDays(3)));

            for (var v : vars) {
                dailyMoods.save(v);
            }
            assertEquals(initialSize + 3, all.size());

            for (var ac : vars) {
                dailyMoods.delete(ac);
            }
            assertEquals(initialSize, all.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            var d = new DailyMoodImpl(5, LocalDate.now().plusDays(5));
            dailyMoods.save(d);
            dailyMoods.update(new DailyMoodImpl(2, LocalDate.now().plusDays(5)));
            all = dailyMoods.getAll();
            assertEquals(initialSize + 1, all.size());
            assertEquals(2, all.get(all.indexOf(d)).getMoodValue());
            d.setMoodValue(3);
            dailyMoods.update(d);
            assertEquals(3, all.get(all.indexOf(d)).getMoodValue());
            dailyMoods.delete(d);
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }
        var d = new DailyMoodImpl(5, LocalDate.now().plusDays(5));
        try {
            dailyMoods.save(d);
            dailyMoods.update(new DailyMoodImpl(5, LocalDate.now().plusDays(6)));
            fail();
        } catch (IllegalArgumentException e) {
            // right
        } catch (DaoAccessException ex) {
            fail();
        }

        try {
            dailyMoods.delete(d);
        } catch (DaoAccessException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(initialSize, all.size());
    }

    @Test
    public void testTodoList() {
        Dao<ToDoAction> toDoList = df.getToDoList();
        int initialSize = toDoList.getAll().size();
        var all = toDoList.getAll();
        try {
            assertEquals(new ToDoActionImpl("Action1", false),
                    new ToDoActionImpl("Action1", true));
            var vars = List.of( new ToDoActionImpl("Action1", false),
                    new ToDoActionImpl("Action2", true),
                    new ToDoActionImpl("Action3", false));

            for (var v : vars) {
                toDoList.save(v);
            }
            assertEquals(initialSize + 3, toDoList.getAll().size());

            for (var ac : vars) {
                toDoList.delete(ac);
            }
            assertEquals(initialSize, toDoList.getAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        var done = new ToDoActionImpl("Action1", false);
        try {
            toDoList.save(done);
            toDoList.update(new ToDoActionImpl("Action1", true));
            assertTrue(all.get(all.indexOf(done)).isDone());
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }

        try{
            toDoList.update(new ToDoActionImpl("ActionNotExisting", true));
            fail();
        }catch (IllegalArgumentException e) {
            // right
        } catch (DaoAccessException ex) {
            fail();
        }

        try {
            toDoList.delete(done);
        } catch (DaoAccessException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(initialSize, all.size());
    }

    @Test
    public void testGroupTransaction() {
        var transactions = df.getGroupTransactions();
        var cats = df.getCategories();
        var colors = df.getColors();
        var persons = df.getPersons();
        var rel = df.getRelationships();
        var all = transactions.getAll();
        int initialSize = all.size();

        var relation = "relation1";
        var c1 = "color1";
        var cat1 = new CategoryImpl("c1", c1);
        var cat2 = new CategoryImpl("c2", c1);
        var p1 = new PersonImpl("Person1", relation);
        var p2 = new PersonImpl("Person2",relation);
        var p3 = new PersonImpl("Person3", relation);

        try {
            colors.save(c1);
            rel.save(relation);
            persons.save(p1);
            persons.save(p2);
            persons.save(p3);
            cats.save(cat1);
            cats.save(cat2);
            List<GroupTransaction> vars = List.of(
                    new GroupTransactionImpl("Transaction1", p1, List.of(p2, p3),
                            300, new LocalDate(2020, 1, 1)),
                    new GroupTransactionImpl("Transaction2", p2, List.of(p1, p3),
                            250, new LocalDate(2020, 1, 1)));
            for (var v : vars) {
                transactions.save(v);
            }
            assertEquals(initialSize + 2, all.size());
            assertEquals(2, all.get(all.indexOf(vars.get(0))).getForList().size());

            for (var v : vars) {
                transactions.delete(v);
            }
        }catch (Exception e) {
            fail();
        }
        final var t = new GroupTransactionImpl("Transaction1", p1, List.of(p2, p3),
                300, new LocalDate(2020, 1, 1));
        try {
            transactions.save(t);
            assertEquals(2, all.get(all.indexOf(t)).getForList().size());
            transactions.update(new GroupTransactionImpl("Transaction1", p1, List.of(p2),
                    300, new LocalDate(2020, 1, 1)));
            fail();
        }catch (IllegalArgumentException e) {
            // success
        } catch (DaoAccessException e) {
            e.printStackTrace();
            fail();
        }
        try{
            transactions.delete(t);
            assertEquals(initialSize, all.size());
            persons.delete(p1);
            persons.delete(p2);
            persons.delete(p3);
            cats.delete(cat1);
            cats.delete(cat2);
            colors.delete(c1);
            rel.delete(relation);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFidelityCards(){
        Dao<FidelityCard> rep = df.getFidelityCards();
        var all = rep.getAll();
        int initialSize = all.size();
        try {
            assertEquals(new FidelityCardImpl("Card1", "0012jada", FidelityCardType.ALIMENTARI),
                    new FidelityCardImpl("Card1", "0012jada", FidelityCardType.ABBIGLIAMENTO));
            var vars = List.of(
                    new FidelityCardImpl("Card1", "0012ada", FidelityCardType.ALIMENTARI),
                    new FidelityCardImpl("Card2", "34232sdd", FidelityCardType.ABBIGLIAMENTO),
                    new FidelityCardImpl("Card3", "232424", FidelityCardType.RISTORAZIONE));

            for (var v : vars) {
                rep.save(v);
            }
            assertEquals(initialSize + 3, rep.getAll().size());
            for (var ac : vars) {
                rep.delete(ac);
            }
            assertEquals(initialSize, rep.getAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        var f =new FidelityCardImpl("Card1", "0012jada", FidelityCardType.ALIMENTARI);
        try {
            rep.save(f);
            rep.update(new FidelityCardImpl("Card1", "0012jada", FidelityCardType.COSMESI));
            assertEquals(FidelityCardType.COSMESI,all.get(all.indexOf(f)).getType());
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }

        try{
            rep.update(new FidelityCardImpl("NotExistingCard", "0012jada", FidelityCardType.ALIMENTARI));
            fail();
        }catch (IllegalArgumentException e) {
            // right
        } catch (DaoAccessException ex) {
            fail();
        }
        try {
            rep.delete(f);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHotkeys(){
        Dao<HotKey> hotKeys = df.getHotKeys();
        var all = hotKeys.getAll();
        int initialSize = all.size();
        try {
            assertNotEquals(new HotKeyImpl("HotKey1", HotKeyType.COUNTER),
                    new HotKeyImpl("Hotkey1", HotKeyType.ACTIVITY));
            var vars = List.of(
                    new HotKeyImpl("H1", HotKeyType.COUNTER),
                    new HotKeyImpl("H2", HotKeyType.ACTIVITY),
                    new HotKeyImpl("H3", HotKeyType.EVENT));

            for (var v : vars) {
                hotKeys.save(v);
            }
            assertEquals(initialSize + 3, all.size());

            for (var ac : vars) {
                hotKeys.delete(ac);
            }
            assertEquals(initialSize, all.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        try {
            var g = new HotKeyImpl("H1", HotKeyType.COUNTER);
            var h = new HotKeyImpl("H1", HotKeyType.ACTIVITY);
            hotKeys.save(g);
            hotKeys.save(h);
            hotKeys.delete(g);
            assertEquals(HotKeyType.ACTIVITY,all.get(all.indexOf(h)).getType());
            hotKeys.delete(h);
            assertEquals(initialSize, all.size());
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }
    }
    @Test
    public void testGroup(){
        var relationships = df.getRelationships();
        var persons = df.getPersons();
        var group = df.getGroup();
        var all = group.getAll();
        int initialSize = all.size();
        var relation = "figlio";
        var p1 = new PersonImpl("person1", relation);
        var p2 = new PersonImpl("person2", relation);
        try {
            relationships.save(relation);
            persons.save(p1);
            group.save(p1);
            assertEquals(initialSize + 1,all.size());
            persons.save(p2);
            group.save(p2);
            assertEquals(initialSize + 2,all.size());

            group.delete(p1);
            group.delete(p2);
            persons.delete(p1);
            persons.delete(p2);
            assertEquals(initialSize, all.size());
            relationships.delete(relation);
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }
        try {
            persons.delete(new PersonImpl("PersonNotExisting", relation));
            fail();
        } catch (IllegalArgumentException e) {
            // success
        } catch (DaoAccessException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testQuickTransaction(){
        var categories = df.getCategories();
        var colors = df.getColors();
        var accounts = df.getAccounts();
        var quickTransactions = df.getQuickTransactions();
        var all = quickTransactions.getAll();
        int initialSize = all.size();
        var c1 = "color1";
        var cat1 = new CategoryImpl("Cat1", c1);
        var ac1 = new AccountImpl("Ac1",c1,200);
        var q1 = new QuickTransactionImpl(300,cat1, ac1, "random1");
        var q2 = new QuickTransactionImpl(500,cat1, ac1, "random2");
        try {
            colors.save(c1);
            categories.save(cat1);
            accounts.save(ac1);
            quickTransactions.save(q1);
            quickTransactions.save(q2);
            assertEquals(initialSize + 2, all.size());
            quickTransactions.delete(q1);
            quickTransactions.delete(q2);
            accounts.delete(ac1);
            categories.delete(cat1);
            colors.delete(c1);
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }
    }
}
