package com.umkc.team11.smartshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import junit.framework.Assert;

import java.util.ArrayList;

/**
 * Created by CJ on 3/4/2018.
 */

public class SearchData extends SQLiteOpenHelper
{
    // database info
    private static final String DATABASE_NAME = "SearchData";
    private static final int DATABASE_VERSION = 1;

    // table names
    private static final String DATA_TABLE_ITEMS = "ITEMS";

    // Item column names
    private static final String KEY_ITEM_ID = "ID";
    private static final String KEY_ITEM_NAME = "NAME";
    private static final String KEY_ITEM_CREATOR = "CREATOR";
    private static final String KEY_ITEM_PRICE = "PRICE";
    private static final String KEY_ITEM_SIZE = "SIZE";
    private static final String KEY_ITEM_STORE = "STORE";
    private static final String KEY_ITEM_DESCRIPTION = "DESCRIPTION";
    private static final String KEY_ITEM_IMAGE = "IMAGE";
    private static final String KEY_ITEM_COLOR = "COLOR";

    // static variable for singleton
    private static SearchData sInstance;

    // gets the singleton instance
    public static synchronized SearchData getInstance(Context context)
    {
        // get the database
        if (sInstance == null)
        {
            sInstance = new SearchData(context.getApplicationContext());
        } // end if

        return sInstance;
    } // end getInstance

    // Constructor
    protected SearchData(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    } // end constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        // build table creation query for lists
        String DATA_TABLE_CREATE =
                "CREATE TABLE " + DATA_TABLE_ITEMS + " (" +
                        KEY_ITEM_ID + " INTEGER PRIMARY KEY, " +
                        KEY_ITEM_NAME + " TEXT, " +
                        KEY_ITEM_CREATOR + " TEXT, " +
                        KEY_ITEM_PRICE + " DOUBLE, " +
                        KEY_ITEM_SIZE + " TEXT, " +
                        KEY_ITEM_STORE + " TEXT, " +
                        KEY_ITEM_DESCRIPTION + " TEXT, " +
                        KEY_ITEM_COLOR + " TEXT, " +
                        KEY_ITEM_IMAGE + " TEXT);";

        // execute table creation
        sqLiteDatabase.execSQL(DATA_TABLE_CREATE);

        addListItem(sqLiteDatabase, "Cotton Hardware Dress", "Karen Scott", 44.50, "XXL", "Macys", "Comfy in cotton, Karen Scott's sheath dress is seamed along the neckline and finished with delicate hardware details\n" +
                "Knee length\n" + "Scoop neckline with hardware detail\n" + "3/4-sleeves\n" + "Unlined\n" + "All cotton\n" + "Machine washable\n" + "Imported", "Blue", "cottonhardwaredress");
        addListItem(sqLiteDatabase, "Ruffle-Hem Fit and Flare Dress","Bar III", 79.50, "0, 2, 4, 6, 8, 10, 12", "Macys", "Romanticized by ruffles and chic bishop sleeves, this fit and flare dress from Bar III is designed as a go-to piece for every occasion\n" +
                "Hidden back zipper closure\n" + "Short length\n" + "Scoop neckline; fit and flare silhouette\n" + "Long bishop sleeves\n" + "Ruffled hem\n" + "Unlined\n" + "Rayon, lining: polyester\n" + "Machine washable\n" + "Imported",
                "Blue", "rufflehemflaredress");
        addListItem(sqLiteDatabase, "Embellished Dress and Duster Jacket", "R and M Richards", 109.00, "6, 8, 10, 12, 14, 16, 18", "Macys", "R and M Richards' elegant mesh-pleated duster jacket looks simply ravishing over a sophisticated occasion dress embellished at the scoop\n" +
                "Jacket: no collar, open front, illusion 3/4-sleeves with sequined lace cuffs, mesh godet pleats, unlined, hits below waist\n" + "Pullover style\n" + "Knee length\n" + "Scoop neckline with sequined lace detail; Shift silhouette\n" + "IMPORTANT NOTE: This item arrives with a return tag attached and instructions for removal. Once the tag is removed from the dress, this item cannot be returned.\n" +
                "Dress: polyester/spandex, trim: polyester/nylon/spandex, lining: polyester, jacket and trim: polyester/spandex, cuff:polyester/nylon/spandex\n" + "Hand wash\n" + "Imported", "Brown", "embellisheddressdusterjacket");
        addListItem(sqLiteDatabase, "Women's Short Sleeve V-Neck Wrap Dress", "Xhilaration", 24.99, "XS, S, M, L, XL, XXL", "Target", "A look that can take you from day to night — this Short-Sleeve Surplice Wrap Dress from Xhilaration brings just the right amount of casual comfort mixed with sweet elements\n" +
                " Styled with a surplice neckline and wrap front, you'll get a flattering fit each time you slip into this dress\n The bold pattern instantly adds a fun flair, whether you pair the dress with sandals during the day or chunky heels for a night out\n" +
                "Sizing: Junior\n" + "Material: 100% Rayon", "Red", "shortvneckwrapdress");
        addListItem(sqLiteDatabase, "Women's Ponte Fit and Flare Dress", "A New Day", 24.99, "XL, XXL", "Target", "For a stylish piece that you can wear for any occasion, from work to dinner dates to running errands, look no further than this Ponte Fit and Flare Dress from A New Day\n" +
                "The soft, stretchy fabric will keep you comfortable from day to night no matter what you're up to while the tapered waist creates a flattering silhouette and transitions to a flowy skirt\n" + "Keep it casual by wearing it with sandals, or dress it up a bit by pairing it with heels\n" +
                "Sizing: Women\n" + "Material: 68.0% Polyester, 30.0% Rayon and 2.0% Spandex", "Orange", "pontefitflaredress");
        addListItem(sqLiteDatabase, "Young Men's Performance Polo", "Dickies", 15.99, "S, M, L, XL", "Target", "Your little guy will love the comfort and ease of wearing this Performance Polo from Dickies\n" +
                "With an easy-to-wear pullover, short-sleeve style, he'll love getting dressed all by himself in the morning\n" + "The polo is ideal for pairing with classic khakis or jeans for a smart-casual look\n" + "Sizing: Men\n" +
                "Material: 100.0% Polyester", "Blue", "performancepoloshirt");
        addListItem(sqLiteDatabase, "Women's Long Sleeve Pocket Casual Loose T-Shirt Dress", "Unbranded", 18.99, "S, M, L, XL", "Amazon", "This super comfy jersey knit dress has a rounded neckline and long sleeve with hidden side seam pockets\n" +
                "Comfy swing silhouette flares gently to a perfect finish\n" + "Perfect dress to hang around the house with or go out and do errands with\n" + "It can also look sexy if you pair it with a belt for a night out\n" + "Hand wash cold/Hang or line dry\n" +
                "This simple and versatile dress is such a year-round essential!\n" + "The soft material is perfect for any season - you can add a jacket and leggings for fall or wear with sandals for a summer look!\n" + "It features a flared look, cute long sleeve, and pockets on each side for a relaxed and practical look! Just add boots to complete this gorgeous look!\n" +
                "And you can easily layer it with a scarf for a cozy winter look, too!\n" + "Unlined\n" + "95% Rayon, 5% Spandex\n" + "Runs true to size, but does have a flared cut and relaxed fit\n" +
                "Fabric: Fabric is very stretchy ", "Black", "longsleevepocketcasualloosedress");
        addListItem(sqLiteDatabase, "Women's Casual Flare Floral Contrast Evening Party Mini Dress", "Miusol", 25.99, "S, M, L, XL, XXL", "Amazon", "60% Rayon, 34% Nylon, 6% Spandex\n" + "Imported\n" + "Deep V-Neck, Sleeveless\n" +
                "Flare Pattern Contrast,Above Knees\n" + "Zipper On The Left Side, Slim Fitting\n" + "Hand Wash Only, Low Temperature for Ironing", "Blue", "casualflarefloral");
        addListItem(sqLiteDatabase, "Men's X-Temp Performance Polo Shirt", "Hanes", 33.50, "S ,M, L, XL, XXL ,XXXL", "Amazon", "Imported\n" + "Machine Wash\n" + "Short-sleeve polo with three-button placket and tag-free labeling\n" +
                "Hanes X-Temp technology dries faster as body heat rises, helping maintain cool temperature\n" + "40+ UPF rating for UV protection\n" + "5.5 oz. ring-spun cotton blend\n" + "5.4 oz. pre-shrunk 60/40 ringspun cotton/polyester\n" +
                "Moisture-wicking properties\n" + "Vapor control technology adjusts to body temperature\n" + "Welt collar", "Blue", "xtempperformancepoloshirt");
        addListItem(sqLiteDatabase, "Men's Advantage Performance Solid Polo", "IZOD", 79.91, "S, M, L, XL, XXL", "Amazon", "60% Cotton, 40% Polyester\n" + "Imported\n" + "Button closure\n" +
                "Machine Wash\n" + "Wicking\n" + "Natural Stretch\n" + "Roll Resistant Collar\n" + "UPF 15 Sun Control", "Grey", "advantageperformancesolidpolo");
    } // end onCreate

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        // if the versions have changed
        if(oldVersion != newVersion)
        {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATA_TABLE_ITEMS);
            onCreate(sqLiteDatabase);
        } // end if
    } // end onUpgrade

    // Add an item to the Items table
    // pass in the name of the list as a string
    private void addListItem(SQLiteDatabase sqLiteDatabase, String name, String creator, double price, String size, String store, String description, String color, String image)
    {
        if (AssertSettings.PRIORITY1_ASSERTIONS)
        {
            // Assert if name is null
            Assert.assertNotNull("name is null", name);
        } // end if

        // get values
        ContentValues values = new ContentValues();

        // put the values into the corresponding location
        values.put(KEY_ITEM_NAME, name);
        values.put(KEY_ITEM_CREATOR, creator);
        values.put(KEY_ITEM_PRICE, price);
        values.put(KEY_ITEM_SIZE, size);
        values.put(KEY_ITEM_STORE, store);
        values.put(KEY_ITEM_DESCRIPTION, description);
        values.put(KEY_ITEM_COLOR, color);
        values.put(KEY_ITEM_IMAGE, image);

        // attempt to insert
        sqLiteDatabase.insertOrThrow(DATA_TABLE_ITEMS, null, values);
    } // end addListItem


    // Get all list items with same listID in the database
    public ArrayList<ItemData> getShoppingItems(String search)
    {
        // Select all items with same listID
        String LISTS_SELECT_QUERY = "SELECT * FROM " + DATA_TABLE_ITEMS + " WHERE (" + KEY_ITEM_NAME + " LIKE \"%" +
                search + "%\");" + " OR (CREATOR LIKE \"%" + search + "%\") OR (DESCRIPTION LIKE \"%" + search +
                "%\") OR (COLOR LIKE \"%" + search + "%\");";

        // gets the database
        SQLiteDatabase db = getReadableDatabase();

        // set cursor to new data
        Cursor cursor = db.rawQuery(LISTS_SELECT_QUERY, null);

        // create list to hold data
        ArrayList<ItemData> data = new ArrayList<ItemData>();

        // attempt to get data
        try
        {
            // move cursor to start
            if (cursor.moveToFirst())
            {
                // loop through data
                do
                {
                    // create ListItemData item
                    ItemData dataCall =
                            new ItemData(cursor.getInt(cursor.getColumnIndex(KEY_ITEM_ID)),
                                    cursor.getString(cursor.getColumnIndex(KEY_ITEM_NAME)),
                                    cursor.getString(cursor.getColumnIndex(KEY_ITEM_CREATOR)),
                                    cursor.getDouble(cursor.getColumnIndex(KEY_ITEM_PRICE)),
                                    cursor.getString(cursor.getColumnIndex(KEY_ITEM_SIZE)),
                                    cursor.getString(cursor.getColumnIndex(KEY_ITEM_STORE)),
                                    cursor.getString(cursor.getColumnIndex(KEY_ITEM_DESCRIPTION)),
                                    cursor.getString(cursor.getColumnIndex(KEY_ITEM_COLOR)),
                                    cursor.getString(cursor.getColumnIndex(KEY_ITEM_IMAGE)));

                    // add ListItemData item to list
                    data.add(dataCall);
                } while(cursor.moveToNext()); // end loop
            } // end if
        }
        catch (Exception e)
        {
            Log.d("getShoppingItems", "Error while trying to get items from database");
        }
        finally
        {
            // close the cursor if not done already
            if (cursor != null && !cursor.isClosed())
            {
                cursor.close();
            } // end if

            // close connection
            db.close();
        } // end try/catch

        // return list of data
        return data;
    } // end getListItems
} // end class SearchData