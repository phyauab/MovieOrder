package com.example.clement.movieorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    //table client
    public static final String TABLE_NAME = "client";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_CREDIT = "credit";

    //table films
    public static final String TABLE_NAME2 = "film";
    public static final String COLUMN_ID2 = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_WRITER = "writer";
    public static final String COLUMN_STARS = "stars";
    public static final String COLUMN_TYPES = "types";
    public static final String COLUMN_MINS = "mins";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PLOT = "plot";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_URL = "url";

    //table myMovie
    public static final String TABLE_NAME3 = "myMovie";
    public static final String COLUMN_OWNER = "owner";
    public static final String COLUMN_OWNED = "owned";

    private static final String DATABASE_NAME = "umovie.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_USERNAME + " text not null, "
            + COLUMN_PASSWORD + " text not null, "
            + COLUMN_AGE + " text not null, "
            + COLUMN_CREDIT + " integer not null"
            + ");";

    private static final String DATABASE_CREATE2 = "CREATE TABLE " + TABLE_NAME2
            + "("
            + COLUMN_ID2 + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_YEAR + " integer not null, "
            + COLUMN_DIRECTOR + " text not null, "
            + COLUMN_WRITER + " text not null, "
            + COLUMN_STARS + " text not null, "
            + COLUMN_TYPES + " text not null, "
            + COLUMN_MINS + " text not null, "
            + COLUMN_PRICE + " text not null, "
            +COLUMN_PLOT + " text not null, "
            +COLUMN_RATING + " integer not null, "
            +COLUMN_URL + " text not null "
            + ");";

    private static final String DATABASE_CREATE3 = "CREATE TABLE " + TABLE_NAME3
            + "("
            + COLUMN_OWNER + " text not null, "
            + COLUMN_OWNED + " text not null "
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(MySQLiteHelper.class.getName(),
                "CREATE TABLE SQL: " + DATABASE_CREATE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE2);
        db.execSQL(DATABASE_CREATE3);

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
                + "1, "
                + "'mary', "
                + "'mary123', "
                + "'18',"
                + "1000"
                + ");"
        );

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
                + "2, "
                + "'john', "
                + "'john123', "
                + "'19',"
                + "1000"
                + ");"
        );

        db.execSQL(
        "INSERT INTO " + TABLE_NAME2 + " VALUES (" +
                "1, 'Fantastic Beasts: The Crimes of Grindelwald', 2018 , 'David Yates', 'J.K. Rowling', 'Eddie Redmayne, Katherine Waterston, Dan Fogler', 'Adventure, Family, Fantasy', '2h 14min', 110, 'In an effort to thwart Grindelwalds plans of raising pure-blood wizards to rule over all non-magical beings, Albus Dumbledore enlists his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.', 7, 'vvFybpmyB9E' )," +
                "(2, 'Bohemian Rhapsody', 2018, 'Bryan Singer', 'Anthony McCarten, Peter Morgan', 'Rami Malek, Lucy Boynton, Gwilym Lee', 'Biography, Drama, Music', '2h 14min', 120, 'Bohemian Rhapsody is a foot-stomping celebration of Queen, their music and their extraordinary lead singer Freddie Mercury. Freddie defied stereotypes and shattered convention to become one of the most beloved entertainers on the planet. The film traces the meteoric rise of the band through their iconic songs and revolutionary sound. They reach unparalleled success, but in an unexpected turn Freddie, surrounded by darker influences, shuns Queen in pursuit of his solo career. Having suffered greatly without the collaboration of Queen, Freddie manages to reunite with his bandmates just in time for Live Aid. While bravely facing a recent AIDS diagnosis, Freddie leads the band in one of the greatest performances in the history of rock music. Queen cements a legacy that continues to inspire outsiders, dreamers and music lovers to this day.', 8, 'mP0VHJYFOAU')," +
                "(3, 'Outlaw King', 2018, 'David Mackenzie','Bathsheba Doran','Chris Pine, Stephen Dillane, Rebecca Robin', 'Action, Biography, Drama', '2h 1min', 130, 'After being declared \"Outlaw\" by the occupying English Empire, Robert the Bruce (Chris Pine) raises an army of Scottish fighters in rebellion. Twists and turns all across the Scottish countryside lead this film on an epic, \"true to historical events\", journey that captures heroism at its core!', 7, 'Q-G1BME8FKw')," +
                "(4, 'Pokémon Detective Pikachu', 2019, 'Rob Letterman','Nicole Perlman', 'Justice Smith, Ryan Reynolds, Suki Waterhouse','Animation, Adventure, Family', '/N', 110, 'The story begins when ace detective Harry Goodman goes mysteriously missing, prompting his 21-year-old son Tim to find out what happened. Aiding in the investigation is Harrys former Pokémon partner, Detective Pikachu: a hilariously wise-cracking, adorable super-sleuth who is a puzzlement even to himself. Finding that they are uniquely equipped to communicate with one another, Tim and Pikachu join forces on a thrilling adventure to unravel the tangled mystery. Chasing clues together through the neon-lit streets of Ryme City--a sprawling, modern metropolis where humans and Pokémon live side by side in a hyper-realistic live-action world--they encounter a diverse cast of Pokémon characters and uncover a shocking plot that could destroy this peaceful co-existence and threaten the whole Pokémon universe.', 10, '1roy4o4tqQM')," +
                "(5, 'Overlord', 2018, 'Julius Avery', 'Billy Ray','Jovan Adepo, Wyatt Russell, Mathilde Ollivier', 'Action, Adventure, Horror', '1h 50min', 130, 'On the eve of D-Day, the 5th of June, 1944, several American paratroopers are dropped behind enemy lines to carry out a mission crucial to the invasions success: destroy a radio tower built in a little castle of an old French town that the Third Reich uses for communication between Berlin and Normandy beaches bunkers. Due to the intense enemy fire, the planes are shot down and most soldiers die in the landing or are killed by the Nazis night patrols after they taking land. However, a private named Ed Boyce survives to find Corporal Ford, a last-minute incorporation from Italy and a veteran expert in bombs and explosives, rogue sniper Tibbet, war photographer Chase, and finally private Dawson. After they watch the killing of their superior Sargeant Eldson by a Nazi night patrol, Ford turns in the leader of the group and they try to get the town with the tower in order to complete the mission.', 7, 'kjVCeKS3jBs')," +
                "(6, 'The Grinch', 2018, 'Yarrow Cheney', 'Michael LeSieur','Benedict Cumberbatch, Cameron Seely, Rashida Jones', 'Animation, Comedy, Family', '1h 26min', 140, 'For their eighth fully animated feature, Illumination and Universal Pictures present The Grinch, based on Dr. Seuss beloved holiday classic. The Grinch tells the story of a cynical grump who goes on a mission to steal Christmas, only to have his heart changed by a young girls generous holiday spirit. Funny, heartwarming, and visually stunning, its a universal story about the spirit of Christmas and the indomitable power of optimism. Academy Award® nominee Benedict Cumberbatch lends his voice to the infamous Grinch, who lives a solitary life inside a cave on Mt. Crumpet with only his loyal dog, Max, for company. With a cave rigged with inventions and contraptions for his day-to-day needs, the Grinch only sees his neighbors in Whoville when he runs out of food. Each year at Christmas they disrupt his tranquil solitude with their increasingly bigger, brighter, and louder celebrations. When the Whos declare they are going to make Christmas three times bigger this year, the Grinch ...', 6, 'Bf6D-i8YpHg\n')," +
                "(7, 'Toy Story 4', 2019, 'Josh Cooley','John Lasseter', 'Tom Hanks, Keanu Reeves, Patricia Arquette', 'Animation, Adventure, Comedy', '/N', 120, 'When a new toy called \"Forky\" joins Woody and the gang, a road trip alongside old and new friends reveals how big the world can be for a toy.', 10, 'LDXYRzerjzU\n')," +
                "(8, 'A Star Is Born', 2018, 'Bradley Cooper', 'Eric Roth','Lady Gaga, Bradley Cooper, Sam Elliott', 'Drama, Music, Romance', '2h 16min', 140, 'Seasoned musician Jackson Maine (Bradley Cooper) discovers-and falls in love with-struggling artist Ally (Gaga). She has just about given up on her dream to make it big as a singer - until Jack coaxes her into the spotlight. But even as Allys career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.', 8, 'hbo_efYhYU2A')," +
                "(9, 'Widows', 2018, 'Steve McQueen', 'Gillian Flynn', 'Viola Davis, Michelle Rodriguez, Elizabeth Debicki','Crime, Drama, Romance', '2h 9min', 100, '\"Widows\" is the story of four women with nothing in common except a debt left behind by their dead husbands criminal activities. Set in contemporary Chicago, amid a time of turmoil, tensions build when Veronica (Viola Davis), Alice (Elizabeth Debicki), Linda (Michelle Rodriguez) and Belle (Cynthia Erivo) take their fate into their own hands and conspire to forge a future on their own terms.', 8, 'nN2yBBSRC78')," +
                "(10, 'The Girl in the Spiders Web', 2018, 'Fede Alvarez', 'David Lagercrantz', 'Claire Foy, Beau Gadsdon, Sverrir Gudnason', 'Crime, Drama, Thriller', '1h 5min', 150, 'Young computer hacker Lisbeth Salander and journalist Mikael Blomkvist find themselves caught in a web of spies, cybercriminals and corrupt government officials.', 6, 'XKMSP9OKspQ')," +
                "(11, 'Peppermint', 2018, 'Pierre Morel', 'Chad St. John', 'Jennifer Garner, John Gallagher Jr., John Ortiz', 'Action, Drama, Thriller', '1h 41min', 120, 'Five years after her husband and daughter are killed in a senseless act of violence, a woman comes back from self-imposed exile to seek revenge against those responsible and the system that let them go free.', 7, 'eeBMQpzoEXQ')," +
                "(12, 'The Meg', 2018, 'Jon Turteltaub', 'Dean Georgaris, Jon Hoeber, Erich Hoeber', 'Jason Statham, Bingbing Li, Rainn Wilson', 'Action, Horror, Sci-Fi', '1h 53min', 110, 'After escaping an attack by what he claims was a 70-foot shark, Jonas Taylor must confront his fears to save those trapped in a sunken submersible.', 6, 'udm5jUA-2bs')," +
                "(13, 'Mission: Impossible - Fallout', 2018, 'Christopher McQuarrie', 'Christopher McQuarrie, Bruce Geller', 'Tom Cruise, Henry Cavill, Ving Rhames', 'Action, Adventure, Thriller', '2h 17min', 120, 'Ethan Hunt and his IMF team, along with some familiar allies, race against time after a mission gone wrong.', 8, 'XiHiW4N7-bo')," +
                "(14, 'The Princess Switch', 2018, 'Mike Rohl', 'Robin Bernheim, Megan Metzger', 'Vanessa Hudgens, Sam Palladio, Nick Sagar', 'Romance', '1h 41min', 130, 'Stacy, a type-a planner with a penchant for schedules, takes a spontaneous trip to the charming land of Belgravia at the encouragement of her sous chef and best friend, Kevin. While preparing for the Royal Christmas Baking Contest, Stacy finds herself faced with an identical stranger, and a strange request. Free-spirited but royally obligated, Duchess Margaret Delacourt wants nothing so much as one last chance to experience life as a \"normal person\" before she dutifully marries the crown Prince of Belgravia. Stacy can give her that wish. From questionable equestrian skills and the true meaning of charity to slipping accents and snowball fights, this is an adventure that no holiday romantic should miss.', 6, '-WBhj57fHeI')," +
                "(15, 'The Favourite', 2018, 'Yorgos Lanthimos', 'Deborah Davis, Tony McNamara', 'Olivia Colman, Emma Stone, Rachel Weisz', 'Biography, Comedy, Drama', '1h 59min', 120, 'In early 18th century England, a frail Queen Anne (Colman) occupies the throne and her close friend Lady Sarah (Weisz) governs the country in her stead. When a new servant Abigail (Stone) arrives, her charm endears her to Sarah.', 8, 'SYb-wkehT1g')," +
                "(16, 'Captain Marvel', 2019, 'Anna Boden, Ryan Fleck', 'Anna Boden, Gene Colan, Liz Flahive', 'Gemma Chan, Brie Larson, Jude Law', 'Action, Adventure, Sci-Fi', '1h 49min', 130, 'Carol Danvers becomes one of the universes most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.', 7, 'Z1BCujX3pw8')," +
                "(17, 'Mile 22', 2018, 'Peter Berg', 'Lea Carpenter, Graham Roland', 'Mark Wahlberg, Lauren Cohan, Iko Uwais', 'Action, Adventure, Crime', '1h 34min', 120, 'An elite American intelligence officer, aided by a top-secret tactical command unit, tries to smuggle a mysterious police officer with sensitive information out of Indonesia.', 6, 'eJU6S5KOsNI')," +
                "(18, 'Green Book', 2018, 'Peter Farrelly', 'Nick Vallelonga, Brian Hayes Currie, Peter Farrelly', 'Viggo Mortensen, Mahershala Ali, Linda Cardellini', 'Biography, Comedy, Drama', '2h 9min', 140, 'A working-class Italian-American bouncer becomes the driver of an African-American classical pianist on a tour of venues through the 1960s American South.', 8, 'uC-_Gon2p9M')," +
                "(19, 'Deadpool 2', 2018, 'David Leitch', 'Rhett Reese, Paul Wernick, Ryan Reynolds', 'Ryan Reynolds, Josh Brolin, Morena Baccarin', 'Action, Adventure, Comedy', '1h 59min', 150, 'Foul-mouthed mutant mercenary Wade Wilson (AKA. Deadpool), brings together a team of fellow mutant rogues to protect a young boy with supernatural abilities from the brutal, time-traveling cyborg, Cable.', 8, 'D86RtevtfrA')," +
                "(20, 'The Nun', 2018, 'Corin Hardy', 'Gary Dauberman, James Wan', 'Demián Bichir, Taissa Farmiga, Jonas Bloquet', 'Horror, Mystery, Thriller', '1h 37min', 120, 'A priest with a haunted past and a novice on the threshold of her final vows are sent by the Vatican to investigate the death of a young nun in Romania and confront a malevolent force in the form of a demonic nun.', 6, 'pzD9zGcUNrw')," +
                "(21, 'First Man', 2018, 'Damien Chazelle', 'Josh Singer, James R. Hansen', 'Ryan Gosling, Claire Foy, Jason Clarke', 'Biography, Drama, History', '2h 11min', 150, 'A look at the life of the astronaut, Neil Armstrong, and the legendary space mission that led him to become the first man to walk on the Moon on July 20, 1969.', 8, 'PSoRx87OO6k')," +
                "(22, 'Avengers: Infinity War', 2018, 'Anthony Russo, Joe Russo', 'Christopher Markus, Stephen McFeely, Stan Lee', 'Robert Downey Jr., Chris Hemsworth, Mark Ruffalo', 'Action, Adventure, Fantasy', '2h 29min', 140, 'The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe.', 9, '6ZfuNTqbHE8')," +
                "(23, 'Cam', 2018, 'Daniel Goldhaber', 'Isa Mazzei, Daniel Goldhaber, Isabelle Link-Levy', 'Madeline Brewer, Patch Darragh, Melora Walters', 'Horror, Mystery, Thriller', '1h 34min', 120, 'Alice, an ambitious camgirl, wakes up one day to discover shes been replaced on her show with an exact replica of herself.', 6, 'pN8xZ5WDonk')," +
                "(24, 'The Lego Movie 2: The Second Part', 2019, 'Mike Mitchell, Trisha Gum', 'Michelle Morgan, Dominic Russo, Phil Lord, Christopher Miller', 'Margot Robbie, Alison Brie, Chris Pratt', 'Animation, Action, Adventure', '1h 40min', 100, 'Its been five years since everything was awesome and the citizens are facing a huge new threat: LEGO DUPLO® invaders from outer space, wrecking everything faster than they can rebuild.', 5, 'cksYkEzUa7k')," +
                "(25, 'The Predator', 2018, 'Shane Black', 'Fred Dekker, Shane Black, Jim Thomas', 'oyd Holbrook, Trevante Rhodes, Jacob Tremblay', 'Action, Adventure, Horror', '1h 47min', 120, 'When a young boy accidentally triggers the universes most lethal hunters return to Earth, only a ragtag crew of ex-soldiers and a disgruntled scientist can prevent the end of the human race.', 6, '50_Ala5BKBo')," +
                "(26, 'The Greatest Showman', 2017, 'Michael Gracey', 'Jenny Bicks, Bill Condon', 'Hugh Jackman, Michelle Williams, Zac Efron', 'Biography, Drama, Musical', '1h 45min', 130, 'Celebrates the birth of show business and tells of a visionary who rose from nothing to create a spectacle that became a worldwide sensation.', 8, 'AXCTMGYUg9A')," +
                "(27, 'The Equalizer 2', 2018, 'Antoine Fuqua', 'Richard Wenk, Michael Sloan, Richard Lindheim', 'Denzel Washington, Pedro Pascal, Ashton Sanders', 'Action, Crime, Thriller', '2h 1min', 140, 'Robert McCall serves an unflinching justice for the exploited and oppressed, but how far will he go when that is someone he loves?', 7, 'HyNJ3UrGk_I');"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}

