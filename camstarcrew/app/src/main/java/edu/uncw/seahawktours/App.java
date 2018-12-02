package edu.uncw.seahawktours;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class App extends Application {

    private static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the main data access object
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        boxStore.close();
        boxStore.deleteAllFiles();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();

        // Get the wrapper (Box) for the Book table that lets us store Book objects
        Box<BuildingInfo> buildingBox = boxStore.boxFor(BuildingInfo.class);

        // Initialize with some data
        if (buildingBox.count() == 0) {
            List<BuildingInfo> initialBuildings = new ArrayList<>();
            initialBuildings.add(new BuildingInfo("CIS Building",
                    "The Computer Information Systems (CIS) building was constructed to house the Information Systems and Operations Management Department from the Cameron School of Business and the Department of Computer Science from the College of Arts & Sciences. The CIS Building, a new 51,731 square-foot state-of-the-art facility on the UNCW campus, opened January 10, 2007 for the new school semester. Work on the facility began on July 29, 2004 with the groundbreaking, and competed in December of 2006. The building cost approximately $12.8 million and was funded by the North Carolina State Bond campaign of 2000. \n\n" +
                            "    Housed within the new building is the Financial Markets Room and, thanks to a donation from Edward Jones and over 50 if its financial advisers, the Edward Jones Financial Lab. Both rooms contain a stock board and ticker, however the Edward Jones Financial Lab also includes the Bloomberg Trading Terminal. With this terminal, students and faculty have the ability to trade stocks directly with the New York Stock Exchange, NASDAQ and more. \n\n" +
                            "    The Department of Computer Science and the Department of Information Systems & Operations Management are housed at CIS together and are jointly offering a Master of Science in Computer Science Information Systems (MSCSIS) degree, an intensive graduate program aimed at preparing the student to assume leadership roles in the development and implementation of computer and information systems.",
                    "Outside the Building",
                    "https://library.uncw.edu/web/collections/archives/bnl/cis.html",
                    "cis1"));
            initialBuildings.add(new BuildingInfo("Warwick Center",
                    "Building History " +
                            "The University Center opened in 1991 financed by student fees. Its purpose was to serve as supplementary space to the University Union, house the Post Office, Game Room and the Office of Human Resources. The building has a large multi-purpose ballroom used for both university and public functions. In 1999, a renaming dedication ceremony is when the center took on the official name of Warwick Center. The campus mascot statue, \'Seahawk\' is located at the front entrance of the building.\n" +
                            "    \n\n" +
                            "    Namesake \n\n" +
                            "    Mr. Bob Warwick, a Wilmington native and Wilmington College Alumni (1955), \"has been an outstanding asset to the University\" according to former Chancellor Dr. Leutze. Mr. Warwick completed his undergraduate work at the University of North Carolina, Chapel Hill as a CPA. He became a partner of Lowrimore, Warwick and Co. from 1973 until its merger with Gladry and Pullen CPA in 1992. Mr. Warwick was been a past member of the Greater Wilmington Chamber of Commerce and Committee of 100. His leadership and dedication to UNC Wilmington is apparent through his positions on the Alumni Board, the Seahawk Board and as Chairman of the Foundation and Endowment Boards. From 1989-1997, he sat on the UNCW Board of Trustees and was on the University Board of Governors. Mr. Warwick was also co-chairman, along with Mr. Bruce Cameron, of the capital campaign fund to build this center. This honor is given to him due to his \"professional counsel, philanthropic spirit and love of this campus.\"",
                    "Warwick Center in 2004",
                    "https://library.uncw.edu/web/collections/archives/bnl/14.html",
                    "warwick"));
            initialBuildings.add(new BuildingInfo("Randall Library",
                    "Building History \n \n Funding to construct a library at the new campus location began in 1965 by the Friends of Wilmington College. The first on-site library was housed in Alderman Hall administration building following the move from Isaac Bear Building. In 1968, the original front of Randall Library faced College Road but since its 1980s expansion the entrance now faces Chancellors Walk. This facility contains UNCW Archives, Special Collections (Helen Hagan Room), the Gene Huguelet Conference Room, and the Honors Program offices, along with various faculty offices.\n" +
                            "    \n \n" +
                            "    Namesake \n\n" +
                            "    Dr. William Madison Randall was the third president of Wilmington College (1958-1968). He was born in Michigan and earned his undergraduate degree (1921) and masters degree (1924)from the University of Michigan. In 1929, he graduated summa cum laude from the Hartford Theological Seminary with specialties in languages and library science. He was a committee member of The Carnegie Endowment for International Peace sent to reorganize the Vatican library in 1928. From 1929-1932 he was a consultant for the Carnegie Corporation of New York. Dr. Randall was also managing editor of \"Library Quarterly\" and assistant Dean of Students at University of Chicago. During World War II he was attached to the War Department and stationed in Cairo, Egypt. In 1947, he returned to academics becoming Director of Library at the University of Georgia. From 1948-1951, he held the position of Academic Dean to the United States Merchant Marine Academy. While recuperating from an automobile accident in Wilmington, Dr. Randall became part of the growing Wilmington College, first as a Dean and then as chief administrator. He is credited with the development of the seal and motto (discere aude - learn courageous), which is still used today. It was during his tenure that Wilmington College met requirements for accreditation as a four-year college. Among his numerous publications are \"Principles of College Library Administration\" and \"The Acquisition and Cataloging of Books.\" It is an appropriate honor for the campus library to be named for Dr. William Randall.",
                    "Randall Library in 1999",
                    "https://library.uncw.edu/web/collections/archives/bnl/3.html",
                    "library"));
            initialBuildings.add(new BuildingInfo("Burney Center",
                    "Building History \n\n" +
                            "    Occupied in 1985, the Burney Student Support Center contained the University Bookstore, the Parking/Student ID Office and the Department of Communication Studies, which moved to Leutze Hall in 2003. The dedication ceremony for the center was held November 1989. In 2006, the bookstore moved to the new Fisher Student Center. Renovations of the Burney Student Support Center began in 2006, and the building was reopened in March 2008 as mainly a meetings and events facility. The name was shortened to the \"Burney Center\".\n\n" +
                            "    \n\n" +
                            "    The renovated Burney Center includes a 9,300 square foot ballroom, which seats nearly 600 guests banquet style and close to 1,000 guests auditorium style.\\n\n" +
                            "    \n\n" +
                            "    The Burney Center also features a colonnade connecting the Burney Center with the new Fisher Student Center. A part of the original construction plans, the colonnade project was cut due to a lack of funds. The Parent\\';s Council then decided to raise money for the colonnade to be built and approximately $190,000 was raised to go towards its construction. It was unveiled on April 5, 2008.",
                    "Burney Center circa 1990",
                    "https://library.uncw.edu/web/collections/archives/bnl/15.html",
                    "burney"));
            initialBuildings.add(new BuildingInfo("Bear Hall",
                    "Building History\n\n" +
                            "    Bear Hall opened at its present site in 1972 as the home for the Business and Economics Departments. Currently, the Departments of Computer Science, Mathematics & Statistics, and Philosophy & Religion occupy classrooms, teaching labs and faculty offices. The College of Arts and Science also resides here.\\n\n" +
                            "\n\n" +
                            "    Namesake\n\n" +
                            "    As the inscription on the plaque states Bear Hall is named for, \"The original Isaac Bear Building, located at Market and 13th Streets, served as the first home of Wilmington College, outside the public school system. Classes were first held there in 1947.\" Constructed in 1912, the name of the first building was given by Samuel Bear, Jr. (1854-1916) as a memorial to his brother, Isaac Bear. The Bear family, owners of an intrastate wholesale dry goods firm located on Front Street, made a number of large donations to better the community such as a wing to James Walker Memorial Hospital. Isaac Bear was known as \"one of Wilmington\\';s most capable and highly esteemed businessmen.\" He was a member of North State Lodge of B\\';Nai Brith, past Grand President of District Grand Lodge, and B.P.O.E Elks #532. Upon his death, the Isaac Bear Memorial School served to remember \"the close friendship and devotion between the brothers and their interest in public welfare.\" The Order of the Isaac Bear is an honorary organization composed of faculty members that taught at the site of the original Wilmington College. Once the college built the new campus off of College Road, the Isaac Bear Building was torn down (1961).",
                    "Bear Hall in 1990",
                    "https://library.uncw.edu/web/collections/archives/bnl/19.html",
                    "bear"));


            // ObjectBox is smart enough to handle CRUD on Collections of entities
            buildingBox.put(initialBuildings);

            System.out.println(buildingBox.count());

            for(BuildingInfo book: buildingBox.getAll()){
                System.out.println(book.getName());
            }
        }
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }
}
