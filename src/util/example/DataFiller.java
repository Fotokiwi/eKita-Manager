package util.example;

import application.EKitaManager;
import database.mysql.MySQL;

public class DataFiller implements Runnable {
	
	public String nachnamen = "Lambert,Zander,Marquart,Hornung,Merk,Steinbach,Yildirim,Caldwell,Fricke,Jörg,Sauer,Garbe,Gutierrez,Schwerdtfeger,Hinrichsen,Woods,David,Schön,Schalk,Volkmann,Schweers,Normann,Roy,Rudolf,Deckert,Black,Nguyen,Arndt,Gill,Kahlert,Bishop,Zeitz,Grosse,Schwarzer,Lehner,Hennig,Zinke,Edel,Muth,Schmitz,Fink,Bruder,Müller,Hase,Herrmann,Cordes,Altmann,Damm,Fell,Heinemann,Banks,Späth,Sedlmeier,Wiese,Malek,Bross,Pfeifer,Backes,Kauer,Mutschler,Jasper,Reindl,Dresler,Castro,Lauber,Manthey,Wille,Waldeck,Kalinowski,Backes,Pütz,Mühlbaür,Romero,Sims,Trautmann,Kröger,Hinrichsen,Ufer,Hans,Morales,Dean,Ochs,Meissner,Tews,Lippert,Francis,Seyfert,Söllner,Brandl,Joseph,Boyd,Alvarez,Huth,Kiessling,Bergmann,Fast,Schröder,Schröter,Jones,Bruening,Hernandez,Watson,Park,Wurm,Watkins,Witte,Preuss,Erpel,Foster,Nötzel,Janzen,Perez,Nowak,Rentschler,Ehrmann,Dietrich,Carmen,Hertwig,Sullivan,Doll,Kahle,Bauer,Stevenson,Beyer,Becker,Maass,Herrmann,Waldeck,Cole,Keim,Stephan,Cobb,Gast,Krings,Siedler,Friedrich,Stein,Strasser,Bass,Hilpert,Thomas,Ross,Prinz,Siemer,Fink,Townsend,Bahr,Hanson,Deutsch,Büscher,Thies,Sherman,Pütz,Brill,Fett,Freitag,Jung,Higgins,Scheffler,Galle,Siedler,Brinkmann,Wehmeyer,Eggert,Sasse,Wegner,Gebhardt,Willer,Wehner,Schwegler,Heger,Schauer,Rivera,Lindner,Doyle,Falkenstein,Manke,Williams,Waldhaus,Fels,Paulsen,Gaida,Käser,Mundt,Hirth,Schott,Gregory,Landgraf,Oppermann,Kempf,Faller,Leibold,Robertson,Brennecke,Drechsler,Pope,Viessmann,Nell,Haberkorn,Karle,Heinrichs,Jänsch,Zell,Bartelt,Lüth,Hinze,Colon,Bachmann,Bischoff,Walter,Spangenberg,Schulte,Hennings,Greif,Alberts,Heger,Falter,Benz,Kunkel,Myers,Butz,Janz,Valdez,Dehn,Mahnke,Reimer,Zinke,Wunderlich,Barnett,Seifried,Boss,Französisch,Liedtke,Austin,Sailer,Grube,Falkenstein,Ferstl,Kaluza,Jonas,Junge,Kleine,Süss,Büsch,König,Seitz,Falke,Pfeilmacher,Moran,Stiller,Gebauer,Brinker,Moore,Eckart,Luther,Flores,Grieche,Reyes,Sonntag,Fehrenbach,Petry,Payne,Siemon,Jan,Wessel,Potter,Wassermann,Figueroa,Reese,Abels,Hilger,Lane,Kaczmarek,Kilian,Hetzel,Strasser,Biermann,Kröber,Katzer,Vetter,Fleming,Wege,Köhler,Sieg,Falkenberg,Baierl,Brewer,Bernd,Pratt,Vogel,Weide,Frei,Jahns,Brock,Bradley,Maurer,Franke,Fehrenbach,Breit,Döring,Kennedy,Kersten,Heuser,Fleming,Newton,Atelco,Metz,Heinrich,Tetzlaff,Hofmann,Hirt,Brüggemann,Michel,Garza,Helm,Cruz,Mörs,Zinke,Damm,Schmied,Nitschke,Kaps,Henninger,Becker,Hensel,Maack,Reber,Holz,Behrendt,Böttner,Watson,Murray,Brettschneider,Krause,Seidel,Ziller,Ihle,Zell,Prinz,Kunkel,Laufer,Lippert,Kress,Helm,Rank,King,Martens,Gibbs,Wanke,Hinkelmann,Guerrero,Ortiz,Geyer,Heyder,Brose,Rice,Carmen,Figueroa,Brockmann,Seemann,Reinhold,Wedemeyer,Seeger,Matthes,Freitag,Wecker,Myers,Abels,Schlegel,Scheffler,Ebeling,Hack,Mahr,Siedler,Schaarschmidt,Motz,Adams,Lamm,Assmann,Münstermann,Buchanan,Gibson,Naber,Bachmaier,Hinrichs,Woods,Reinhardt,Washington,Schweiger,Brandstetter,Rieger,Silva,Cortez,Bossert,Hage,Horstmann,Martinez,Ochsen,Brewer,Reinhold,Thieme,Wacker,Gerdes,Bachmeier,Liebe,Mucha,Brink,Löhr,Gehrke,King,Hohmann,Parker,Shaw,Schauer,Grieche,Becher,Degner,Markmann,Bondar,Behrens,Hackmann,Herber,Altmann,Gehrig,Wegmann,Kremer,Reimann,Acker,Brandstetter,Kehl,Reinecke,Kieme,Schwerdtfeger,Scheck,Moos,Rosenthal,Barthel,Schätzle,Diaz,Gerke,Kasper,Wienecke,Cooper,Cremer,Kretzer,Häfner,Copeland,Reineke,Falkenstein,Hielscher,Kretschmann,Ufer,Marschner,Krings,Graf,Breitenbach,Sänger,Kehrer,Kegel,Just,Schröter,Späth,Zinke,Bär,Wienecke,Bauer,Reimer,Neumann,Kaplan,Just,Vollmer,Thomsen,Gass,Rudolph,McCarthy,Wessel,Morgan,Zimmer,Wedel,Schwager,Lambrecht,Tillmann,Fleck,Grünewald,Schiffer,Bradley,McGuire,Wild,Baumgart,Seyfert,Volkmann,Sauermann,Malik,Hansen,Gebel,Raiba,Schmitt,Torres,Gäbler,Brosch,Lenz,Martinez,Evers,Kemper,Fuerst,Manske,Seidl,Michaelis,Pfaff,Tacke,Guerrero,Lohr,Bass,Arndt,Jörg,Bremer,Tate,Michel,Aberle,Wilken,Ferber,Payne,Kelly,Bucher,Hirt,Küster,Lutz,Lanz,Möller,Schuhmacher,Schuh,Ullrich,Remondis,Armstrong,Snyder,Thieme,Bek,Kara,Kassner,Kroll,Büchner,Pauly,Yilmaz,Oberländer,Waller,Büchner,Bös,Ralf,Patzelt,Kempf,Weiß,Schwan,Bräutigam,Weise,Sell,Brodbeck,Jefferson,Bös,Washington,Kurth,Gallus,Jacoby,Kast,Laub,Daniels,Hines,Hubbard,Schardt,Hilbig,Gehring,Willer,Häfele,Gessler,Falkenstein,Wilk,Kainz,Gonzalez,Evers,Pesch,Hanisch,Rost,Houston,Denzer,Hellwig,Tekin,Mann,Mertens,Layer,Dorn,Gonzalez,Arndt,Ochsen,Popp,Seemann,Triebel,Mündel,Kunz,Schüttler,Beck,Aumüller,Dressler,Watkins,Paulus,Krauss,Mahr,Waters,Baker,Wörner,Borchers,Gast,Kohler,Yates,Walz,Anderson,Sauter,Denzer,Schreier,Santiago,Rothe,Vater,Söllner,Burger,Hirschmann,Sutton,Dehmel,Fellner,Fritsche,Tafel,Knapp,Heil,Janzen,Lücke,Felten,Geiger,Mössner,Lukas,Nickel,Aydin,Thiele,Endres,Albers,Acar,Triebel,Richards,Stahlgruber,Goodwin,Vedes,Cummings,Schad,Kieslich,Bär,Manning,Rettig,Ruppert,Wassermann,Rainer,Bäcker,Collins,Riess,Can,Lehmkuhl,Saul,Reed,Ehrhardt,Romero,Jakob,Amann,Volkmann,Patzer,Reinhart,Harms,Debus,Jacob,O'Brien,Brandstetter,Ufer,Holloway,Fleming,Classen,Frank,Konrad,Stein,Farmer,Mühlbaür,Ackermann,Olson,Weiland,Schwaiger,Bartel,Schade,Barton,Nau,Horton,Schädler,Dengler,Demmler,Vetter,Weaver,Betz,Liedtke,Renz,Gehrmann,Klinger,Petri,Wells,Singleton,Henrichs,Zuschuss,Ranft,Perry,Jacob,Weger,Muth,Fritsche,Brück,Fassbender,O'Brien,Krämer,Urban,Schmit,Ehrlich,Barthel,Anders,Morales,Carstensen,Leder,Landwirt,Massey,Naujoks,Herman,Zapf,Rodriguez,Bähr,Brandl,Kowalski,Appel,Thoma,Widmann,Rössler,Webster,Just,Neuhaus,Volz,Huth,Harvey,Dittmann,Bruening,Nichols,Rex,Christ,Leder,Kreutzer,Jankowski,Mahler,Coleman,Eberhardt,Sacher,Kaluza,Gaul,Ziller,Weise,Siedler,Hillenbrand,Mück,Arlt,Sigl,Sperling,Sparks,Gibbs,Oliver,Ehrmann,Mück,Spindler,Abels,Schreiber,Adolph,Hennig,Beckmann,Oppermann,Brady,Bracht,Schwenke,König,Wilk,Bowman,Münch,Teufel,Schädler,Adler,Gehlen,Perkins,Lippert,Reinsch,Neuberger,Oliver,Roberts,Thomsen,Schreiber,Ziemann,Seidler,Kloster,Weide,Wehner,Lehmkuhl,Denker,Aigner,Reisser,Meier,Payne,Tate,Wolters,Simpson,Franklin,Bötcher,Wedemeyer,Steinmann,Manz,Münch,Seger,Ziegler,Pohlmann,Püschel,Townsend,Person,Reusch,Lammert,Kaczmarek,Krey,Salewski,Hans,Kapp,Hagel,Ries,Dehn,Person,Payne,Bussmann,Senioren,Küpper,Ranft,Grant,Grundmann,Frazier,Weaver,Schaller,Layer,Bad,Tröger,Thomas,Janssen,Kahlert,Aumann,Adkins,Veit,Warnke,Freund,Kramer,Kegel,Brockhaus,Moser,Sauermann,Collins,Duncan,Freitag,Beyer,Aumann,Jensen,Jacobs,Rehm,Bartelt,Ganz,Hagen,Lindner,Willer,Linke,Kayser,Riley,Kaden,Röder,Pütz,Kampe,Ahrendt,Lachmann,Enders,Riedl,Bretz,Brecht,Kling,Ferber,Söllner,Dehmel,Tröger,Krebs,Munk,Fast,Markmann,Pauly,Renate,Mitchell,Mason,Mühl,Lehr,Leonhardt,Feist,Häcker,Price,Linder,Ford,Carson,Durchstechen,Jonas,Howard,Grundmann,Jäschke,Karsten,Lederer,Frenzel,Pape,Fisher,Gallus,Hauke,Ahrens,Gill,Schäfer,Seidl,Feist,Ostermann,Keiser,Waldeck,Hinzmann,Hirschmann,Fuller,Mündel,Janke,Bishop,Behnke,Kassner,Clarke,Kalisch,Foster,Barth,Schmid,Pätzold,Sautter,Bross,Bachmann,Demir,Kirsch,Stiller,Bade,Fleck,Deckert,Seifried,Pätzold,Mahler,Kehrer,Kühne,Genz,Sommerfeld,Lammert,Denecke,Puls,Hocke,Elsner,Faller,Jaschke,Eberhardt,Hintze,Brockmann,Gordon,Dean,Baron,Keil,Eberspächer,Kaps,Dahlmann,Laumann,Bartsch,Faust,Radtke,Brewer,Parks,Shelton,Reich,Steuermann,Maisch,Jaschke,Boyd,Caldwell,Ernst,Schnell,Hoch,Georg,Larson,Reinke,Mair,Koch,Rogers,Laskowski,Köster";
	
	public String vornamen = "Margarethe,Saskia,Carin,Luis,Malin,Amelie,Margarethe,Jonah,Lyn,Elfriede,Nathalie,Jacob,Claus,Phillipp,Caja,Smilla,Nadin,Nele,Johanna,Angelika,Lucas,Jessica,Joelina,Filip,Eva,Wilhelm,Robert,Keno,Stella,Chantal,Irmgard,Benedict,Finja,Benedict,Dana,Joshua,Karin,Leandro,Jolina,Joana,Laurin,Sofie,Christoph,Alexandra,Sabrina,Luisa,Emely,Reiner,Kian,Nick,Mathis,Kian,Leif,Sebastian,Nils,Siegfried,Fabio,Carlotta,Krista,John,Dominik,Amy,Elke,Gertrud,Connor,Monika,Angelika,Niko,Annette,Oliver,Hertha,Hannes,Kay,Andre,Kim,Günther,Kristian,Sonja,Connor,Günter,Mirco,Leonard,Celina,Ali,Emre,Gabriel,Magdalena,Benedict,Susanne,Anke,Jolina,Jörg,Martin,Dario,Teresa,Richard,Gerda,Dirk,Simon,Steven,Leona,Keno,Ulrike,Mika,Christine,Mathis,Kimberley,Saskia,Jeremy,Selina,Enno,Manfred,Janis,Marina,Karl,Hannah,Konstantin,Milena,Florian,Nico,Mira,Axel,Leon,Julian,Adrian,Sven,Jeremy,Maja,Paulina,Gudrun,Heinz,Linn,Reiner,Jakob,Johanna,Milena,Luna,Philipp,Mona,Klaus,Eva,Marta,Chantalle,Melina,Kjell,Justus,Malte,Justin,Birgit,Carolina,Lotta,Chantall,Levin,Eva,Leonie,Käthe,Christin,Oliver,Rosa,Benedikt,Helmuth,Rike,Marie,Victor,Kaya,Niels,Vincent,Katja,Robert,Anika,Dieter,Gisela,Jason,Lorenz,Keno,Jamie,Dominik,Neele,Nelli,Klaudia,Rieke,David,Connor,Manuel,Hannelore,Jannes,Ida,Claas,Rudolf,Marco,Christel,Brigitte,Otto,Lucy,Maximilian,Björn,Lucas,Susanne,Elias,Kira,Marike,Alyssa,Walther,Klaas,Amelie,Lara,Fabienne,Riccardo,Marc,Mirco,Käthe,Janne,Miguel,Susanne,Andreas,Helga,Claus,Günter,Judith,Frida,Lea,Monika,Reiner,Emily,Jule,Elisa,Liah,Gert,Matthis,Maxim,Nadine,Jannik,Rafael,Norbert,Simon,Swen,Malin,Clara,Kathrin,Christopher,Bruno,Viktoria,Vincent,Emil,Sven,Joelina,Lili,Leona,Lion,Muhammed,Isabell,Christine,Anica,Annica,Nadin,Raphael,Erik,Johanna,Joana,Lynn,Lena,Bernd,Elfriede,Jenny,Keno,Bernd,Jörn,Günter,Justin,Fabian,Jill,Selina,Liv,Raphael,Marieke,Marius,Jannis,Maurice,Ingeborg,Clara,Henriette,Rasmus,Ingrid,Joerg,Jakob,Valentin,Liam,Bernhard,Til,Anika,Nina,Phillipp,Annabell,Waldtraut,Florian,Antje,Kurt,Selina,Bernt,Julian,Ruth,Joelle,Samira,Vincent,Reiner,Chantalle,Noel,Lotta,Rolf,Wolfgang,Cornelia,Milan,Phillip,Joerg,Merle,Lucie,Annette,Lieselotte,Birgit,Lea,Oliver,Phil,Felix,Nils,Waldtraut,Kaja,Manuela,Denise,Luca,Hertha,Alexandra,Yannick,Phil,Ronja,Hannelore,Christel,Janina,Maximilian,Nele,Dustin,Anke,Emmely,Bernd,Waldtraut,Enno,Dennis,Paula,Ayleen,Dagmar,Ida,Nadine,Kiara,Reinhardt,Lorenz,Jennifer,Claudia,Miriam,Diana,Stella,Martin,Nic,Volker,Katja,Uwe,Henning,Smilla,Kian,Elke,Frank,Denise,Luna,Cornelia,Linn,Patrik,Edith,Finja,Ferdinand,Sebastian,Jule,Mia,Jean,Fenja,Frederik,Marah,Max,Johanna,Fabio,Emily,Mila,Marius,Lya,Julie,Fenja,Gerhard,Filip,Jasmin,Tara,Maximilian,Anne,Kaja,Carina,Marlis,Magnus,Martha,Hartmut,Willi,Sylvia,Irmgart,Elisa,Caja,Helen,Annica,Margot,Wilfried,Jannes,Anica,Madeleine,John,Regina,Anastasia,Rebekka,Edith,Jule,Melissa,Amely,Lena,Isabelle,Victor,Catrin,Gert,Jesse,Aileen,Lukas,Daniela,Caja,Luisa,Mike,Theresa,Karsten,Artur,Claas,Carin,Kevin,Gerhardt,Karlotta,Jakob,Magnus,Leah,Jasper,Claus,Malin,Marion,Tore,Frieda,Leona,Amely,Margarethe,Andre,Laurenz,Selina,Silvia,Helen,Elfriede,Layla,Marike,Lina,Carlotta,Frieda,Luka,Sara,Jörn,Wolfgang,Hannes,Annabel,Rosa,Jaqueline,Erich,Claudia,Matthis,Svea,Petra,Frederic,Andrea,Giulia,Luk,Ida,Emre,Sam,Astrid,Jacob,Claudia,Bruno,Lucas,Lilly,Denis,Eva,Nadine,Romy,Jona,Rasmus,Walther,Joelina,Linn,Volker,Stina,Annette,Milan,Ruth,Antonia,Kim,Julie,Charlotte,Lya,Niklas,Thomas,Jamie,Joelina,Matthias,Damian,Ida,Britta,Dagmar,Mathis,Leander,Julie,Marah,Stefan,Rosemarie,Denis,Vanessa,Marko,Lars,Liam,Dirk,Rainer,Marcus,Jannick,Juri,Lucas,Jaqueline,Till,Lion,Renate,Rebekka,Ida,Margarethe,Ricardo,Raphael,Nora,Christa,Louisa,Kyra,Annette,Lia,Jessica,Thorsten,Natalie,Alexandra,Nils,Nikolas,Luc,Theo,Torben,Max,Mandy,René,Muhammed,Karl,Mariella,Barbara,Nele,Tim,Christof,Noah,Conner,Henri,Justus,Selina,Fynn,Meik,Karlotta,Judith,Falk,Rafael,Erica,Andrea,Lynn,Benjamin,Tara,Swenja,Philipp,Jacob,Gerd,Oskar,Fenja,Niko,Irmgard,Laurenz,Saskia,Mia,Berndt,Dominic,Jannick,Chantall,Janne,Ronja,Alena,Paulina,Jana,Artur,Laurens,Margarete,Christiane,Rüdiger,Kristian,Tom,Alfred,Nina,Karlo,Matthies,Celina,Paulina,Lucy,Mario,Amely,Benjamin,Janik,Lucas,Smilla,Finn,Annalena,Sam,Joerg,Lennox,Stephanie,Herta,Matteo,David,Volker,Carolin,Denise,Alina,Rebecca,Niklas,Niels,Saskia,Henrik,Cornelia,Nora,Jannick,Moritz,Marina,Waltraud,Nike,Teresa,Astrid,Antonia,Krista,Sabrina,Kristian,Dilara,Claudia,Janin,Laila,Vanessa,Karolina,Nathalie,Ralph,Niklas,Siegfried,Niklas,Gert,Ella,Torsten,Melvin,Silas,Ruben,Cedrik,Andrea,Jonna,Mia,Justus,Selin,Fabio,Daniela,Frederic,Kristian,Cornelia,Käthe,Karina,André,Benedikt,Niclas,Nico,Kurt,Ernst,Chantall,Jannick,Matthies,Friedrich,Madeleine,Nelli,Christoph,Mario,Ida,Leander,Niklas,Mira,Ivonne,Erik,Sophie,Isabella,Jasmin,Yannic,Sonja,Ernst,Margarete,Wolfgang,Rosa,Xenia,Herta,Christel,Chantal,Wolfgang,Rudolph,Lucas,Steven,Carlotta,Emil,Laura,Sinah,Kristian,Stella,Matthies,Curt,Werner,Paulina,Aleyna,Linda,Mathilda,Elisabeth,Jessika,Nik,Jona,Nelli,Tabea,Benjamin,Jannick,Ricardo,Katja,Jutta,Lotta,Lilian,Xenia,Tore,Rudolph,Margarethe,Keno,Anette,Jacqueline,Laila,Josephine,Cedrik,Alisa,Nico,Noel,Jonathan,Klara,Jette,Diana,Petra,Ina,Mathilda,Catharina,Clara,Cornelia,Clemens,Matteo,Sonja,Matthias,Nora,Lana,Martin,Henry,René,Marlies,Mika,Kjell,Matthies,Lewin,Pascal,Jason,Mert,Mathilda,Anika,Giulia,Philipp,Inge,Reinhard,Aileen,Nele,Ralph,Ole,Stefanie,Robert,Lili,Gina,Finnja,Joelina,Thies,Tristan,Johann,Peter,Arthur,Rebekka,Klemens,Hans,Melvin,Cedrik,Victoria,Isabelle,Catarina,John,Svea,Oskar,Alexa,Bettina,Axel,Maya,Christine,Gertrud,Dilara,Leonardo,Otto,Leon,Patrick,Annette,Emely,Carla,Nicholas,Jessika,Carin,Carla,Erika,Meik,Heike,Juliana,Ullrich,Luca,Juli,Sam,Luis,Mattis,Stina,Curt,Colin,Louisa,Joelle,Damian,Günter,Alina,Kjell,Matti,Günter,Teresa,Waltraut,Joschua,Selina,Melina,Leon,Leah,Mila,Stephan,Chantall,Margarethe,Florian,Helmuth,Joschua,Jörn,Julian,Johannes,Max,Annika,Lennart,Anja,Luise,Jamie,Jutta,Til,Samantha,Florian,Amelie,Klaus,Meike,Timon,Vanessa,Emilia,Henriette,Anthony,Giulia,Rosa,Joachim,Helene,Dustin,Manuel,Rasmus,Nicholas,Lilly,Viktor,Linus,Marcus,Annalena,Alicia,Matthies,Lothar,Samuel,Cedrik,Harald,Lisa,Matis,Jean,Hertha,Arthur,Karina,Paulina,Kristine,Heinz,Otto,Chantall,Dana,Lene,Dieter,Annalena,Charlotte,Marten,Valentin,Helga,Mara,Maximilian,Kathrin,Marco,Christa,Tabea,Juliana,Mark,Alexander,Noel,Rainer,Käthe,Christian,Herbert,Annalena,Denise,Karin,Nike,Jakob,Tom,Henry,Celine,Alicia,Pia,Nikolas,Chris,Richard,Andreas,Kristina,Stefan,Valentin,Joy,Annabell,Maria,Aleyna,Marlies,Gerda,Christine,Rüdiger,Tore,Lucia,Jannick,Giulia,Yannick,Karolina,Amy,Annica,Phil,Markus,Amelie,Anneke,Lorenz,Nadine,Carl,Maren,Erna,Helmut,Rasmus,Denise,Karin,Wilfried,Claas,Emilia,";
	
	public String geburtsdatum = "15.7.1930,15.11.1980,27.8.1927,5.1.1994,2.2.1943,3.10.1939,26.3.1989,6.12.1996,20.11.1998,10.2.1929,3.4.1983,2.8.1927,27.12.1976,8.12.1962,17.7.1961,28.10.1930,14.7.1948,16.4.1959,25.3.1969,20.4.1975,26.8.1934,24.11.1966,25.7.1987,2.12.1967,24.1.1943,27.2.1956,1.12.1982,2.4.1935,25.7.1951,26.4.1991,14.5.1955,17.4.1972,18.3.1928,23.11.1931,8.7.1973,27.2.1948,7.7.1987,1.3.1974,17.3.1944,4.12.1965,1.2.1967,23.10.1972,19.7.1924,15.12.1948,14.7.1943,9.7.1937,6.3.1979,10.12.1924,13.4.1931,6.9.1970,5.11.1998,20.1.1974,24.3.1921,8.8.1937,13.7.1934,26.7.1937,28.12.1931,5.1.1969,13.8.1942,19.12.1927,25.1.1941,23.10.1963,27.2.1920,2.9.1940,9.3.1950,25.7.1979,18.3.1948,16.8.1973,19.7.1946,23.6.1948,5.6.1938,17.10.1939,12.4.1921,22.9.1995,4.1.1966,6.11.1948,4.10.1941,12.10.1940,11.6.1944,21.8.1927,15.4.1965,4.2.1948,1.11.1967,8.12.1942,23.10.1989,26.9.1951,5.1.1992,4.4.1994,20.5.1934,7.9.1995,22.7.1935,7.9.1986,13.11.1949,12.5.1947,15.6.1975,14.11.1962,24.6.1961,2.2.1931,24.9.1999,18.4.1939,4.2.1947,10.1.1959,5.10.1974,4.10.1957,4.6.1954,22.7.1951,20.7.1965,9.3.1995,4.11.1983,18.10.1995,6.7.1958,17.8.1962,24.8.1951,13.5.1992,19.7.1935,24.1.1920,28.12.1941,6.4.1980,15.9.1961,9.7.1934,6.8.1930,23.3.1935,6.8.1995,4.10.1927,19.9.1957,20.1.1958,7.11.1967,11.1.1933,20.10.1948,11.2.1920,12.3.1987,5.8.1981,15.8.1925,9.5.1962,11.9.1998,17.6.1992,23.12.1929,25.5.1957,11.11.1954,20.8.1959,18.12.1997,24.10.1925,11.6.1978,27.5.1952,3.4.1962,19.6.1990,14.9.1957,21.3.1986,4.8.1966,19.4.1946,7.11.1975,16.5.1961,6.11.1957,6.8.1997,26.1.1958,24.2.1930,27.3.1962,10.2.1958,4.12.1964,14.11.1984,18.4.1996,22.10.1964,19.6.1992,20.2.1946,21.10.1934,21.3.1936,19.4.1944,1.9.1937,27.9.1957,6.11.1921,3.5.1944,19.10.1943,1.12.1936,23.1.1953,10.5.1964,27.6.1970,14.5.1984,27.12.1999,22.6.1921,8.3.1977,9.4.1933,14.8.1955,16.1.1964,18.11.1958,9.12.1970,24.3.1943,10.12.1983,20.11.1935,9.1.1979,24.2.1971,17.6.1978,1.8.1995,20.2.1932,13.12.1957,26.1.1945,19.12.1923,19.1.1945,15.1.1994,12.8.1932,27.5.1957,2.9.1939,11.9.1924,3.2.1935,25.7.1957,27.1.1937,16.12.1935,13.9.1939,5.5.1982,8.4.1996,13.6.1946,3.5.1933,11.9.1978,17.3.1996,14.8.1925,10.9.1922,7.5.1975,25.12.1931,17.5.1992,8.3.1966,6.3.1994,3.12.1940,18.4.1971,19.8.1947,3.10.1925,15.2.1920,19.8.1976,14.9.1995,16.3.1941,16.10.1979,1.11.1953,10.9.1995,26.5.1963,26.6.1940,6.10.1974,4.2.2000,28.12.1979,15.9.1995,7.7.1944,4.10.1969,28.7.1921,9.9.1921,2.2.1955,19.1.1928,4.1.1975,14.8.1937,3.10.1990,15.2.1926,22.1.1970,19.6.2000,19.11.1990,4.4.1925,2.2.1976,4.4.1966,18.10.1931,1.11.1972,8.9.1926,27.4.1937,21.4.1922,20.10.1931,28.11.1949,16.4.1956,24.3.1947,10.11.1935,21.2.1965,10.3.1935,15.1.1938,13.4.1996,13.9.1968,27.10.1956,11.4.1981,14.2.1931,21.7.1954,7.3.1942,5.7.1992,22.9.1921,27.11.1993,4.1.1924,15.9.1975,8.3.1955,25.1.1953,15.5.1938,13.5.1939,11.12.1961,5.9.1935,21.6.1963,6.11.1967,24.2.1954,4.8.1972,8.1.1944,1.3.1936,25.5.1941,23.4.1997,9.10.1952,21.10.1922,3.3.1931,10.2.1936,27.8.1999,11.2.1961,21.5.1997,1.6.1952,3.4.1972,13.10.1925,2.5.1974,8.4.1997,22.9.1934,6.11.1925,15.3.1950,1.1.1950,24.2.2000,24.4.1974,9.1.1947,22.11.1996,19.6.1940,12.3.1970,18.8.1921,12.10.1938,19.5.1962,16.10.1999,22.12.1987,8.10.1956,20.1.1939,26.7.1965,1.2.1944,1.4.1994,22.8.1945,24.2.1941,17.2.1993,23.11.1960,13.3.1952,16.10.1984,6.9.1951,14.12.1937,20.3.1935,10.4.1928,8.3.1982,9.5.1983,14.6.1993,9.8.1963,2.8.1997,9.10.1946,20.9.1945,11.8.1969,22.5.1956,6.3.1981,3.3.1989,7.10.1926,26.6.1986,3.12.1992,4.9.1925,9.10.1943,17.5.1938,3.10.1971,11.6.1946,10.3.1947,18.4.1959,27.12.1956,7.9.1936,14.5.1929,13.5.1980,21.12.1973,28.1.1952,23.6.1947,8.12.1934,18.8.1973,1.7.1995,9.7.1989,16.8.1941,2.8.1957,25.6.1959,6.9.1974,9.10.1998,20.8.1962,21.6.1973,1.9.1996,11.10.2000,6.6.1972,20.6.1937,21.1.1945,26.5.1951,11.10.1933,3.5.1968,10.8.1996,12.3.1969,26.11.1987,12.11.1992,13.8.1970,25.11.1993,4.11.1977,16.8.1924,27.3.1940,2.5.1964,13.10.1956,12.3.1928,17.7.1922,28.7.1927,28.11.1982,12.7.1927,6.3.1968,22.5.1955,28.1.1944,16.10.1926,26.11.1920,19.8.1927,13.10.1977,27.2.1986,8.2.1989,17.9.1967,26.7.1946,26.3.1992,1.8.1992,27.9.1932,23.9.1932,5.10.1936,17.9.1928,24.5.1932,7.6.1999,18.9.1945,7.12.1952,15.1.1971,18.11.1997,14.11.1980,18.3.1990,23.12.1943,5.6.1941,1.3.1970,27.9.1936,15.12.1939,6.1.1979,24.4.1942,24.7.1961,15.6.1996,2.2.1944,19.10.1951,4.12.1964,16.7.1944,15.5.1931,23.8.1941,3.4.1962,18.6.1967,5.6.1976,2.2.1986,18.8.1948,8.10.1940,12.2.1951,24.5.1935,5.4.1970,8.12.1971,5.5.1983,20.2.1972,2.11.1928,25.3.1998,16.1.1983,6.10.1959,18.6.1995,7.12.1948,10.7.1987,1.12.1926,21.4.1994,25.10.1987,8.6.1997,26.3.1986,19.8.1924,3.11.1970,4.2.1936,4.10.1931,5.7.1949,28.6.1990,23.12.1963,27.5.1932,5.7.1960,11.5.1985,6.6.1966,14.11.1969,16.10.1976,22.8.1982,5.9.1958,1.1.1959,6.4.1951,1.6.1988,13.10.1924,27.3.1984,26.1.1972,21.2.1959,26.6.1938,12.10.1926,24.8.1921,15.9.1975,10.12.1961,7.3.1995,14.6.1976,11.5.1964,6.7.1925,20.8.1989,26.4.1944,7.7.1957,22.1.1999,15.7.1994,27.12.1949,17.5.1984,1.2.1983,2.8.1943,27.8.1922,28.9.1925,27.7.1988,6.1.1953,25.6.1978,13.8.1928,9.1.1933,7.6.1983,7.1.1947,14.6.1989,21.12.1986,2.3.1959,13.5.1926,25.3.1964,7.7.1939,26.1.1939,21.4.1958,24.12.1966,12.11.1994,25.12.1971,3.9.1937,6.5.1953,3.9.1957,5.10.1975,20.9.1969,23.11.1929,7.2.1978,18.8.1942,7.1.1947,2.2.1925,5.9.1972,10.2.1964,20.3.1991,8.6.1984,20.10.2000,5.3.1938,4.8.1945,14.7.1936,3.6.1996,16.3.1954,8.5.1971,5.7.1938,25.7.1989,12.9.1952,2.10.1966,25.10.1952,13.4.1956,22.1.1972,12.9.1948,11.7.1976,11.2.1960,25.6.1967,4.12.1925,26.10.1993,26.11.1976,12.9.1934,11.12.1992,13.11.1953,10.2.1963,11.6.1952,19.1.1979,7.7.1987,25.4.1946,3.12.1987,5.8.1991,15.8.1977,4.9.1953,11.4.1978,18.12.1936,20.4.2000,4.8.1925,4.12.1932,27.11.1933,21.10.1938,3.12.1979,28.3.1972,19.1.1999,10.6.1976,9.11.1923,4.10.1996,20.10.1930,18.1.1950,25.10.1980,21.6.1986,25.8.1921,4.12.1995,12.9.1981,3.12.1924,22.1.1961,13.6.1999,7.10.1990,17.2.1962,12.5.1930,5.10.1980,11.8.1976,20.8.1948,18.7.1973,26.7.1959,4.7.1953,9.8.1980,3.9.1934,11.2.1967,17.7.1992,16.6.1929,6.11.1925,6.11.1978,11.8.1947,6.2.1971,10.11.1954,10.10.1924,23.6.1969,17.11.1928,20.3.1939,9.9.1965,27.2.1983,20.6.1950,6.2.1996,2.7.1940,23.7.1932,6.8.1963,22.9.1920,24.4.1924,14.9.1988,2.5.1999,13.2.1987,26.1.1994,15.9.1979,25.8.1933,18.12.1971,28.2.1983,18.9.1990,12.1.1986,5.6.1947,9.9.1958,9.10.1976,16.10.1941,10.2.1974,15.5.1940,9.6.1984,1.6.1950,3.10.1962,20.11.1960,17.10.1991,7.7.1928,17.2.1938,17.8.1950,27.9.1931,17.12.1975,14.12.1929,23.2.1969,1.5.1986,16.3.1934,1.1.1967,2.2.1992,14.6.1977,27.12.1981,4.2.1948,5.1.1922,21.4.1989,26.1.1953,24.10.1924,22.7.1984,20.4.1999,18.11.1925,24.11.1997,27.10.1950,18.9.1940,5.5.1965,15.3.1930,26.9.1976,14.5.1964,23.10.1933,8.11.1929,22.9.1960,27.6.1962,20.6.1946,15.9.1979,20.9.1920,6.7.1973,17.2.1980,5.7.1984,26.11.1975,20.2.1972,3.7.1999,28.12.1951,15.9.1924,17.8.1945,5.7.1958,17.10.1989,14.2.1935,1.8.1921,12.4.1931,18.5.1941,6.2.1988,3.3.1953,3.2.1980,14.6.1930,27.3.1952,27.3.1943,7.8.1949,22.8.1999,15.4.1998,15.2.1931,8.3.1951,25.9.1977,17.1.1931,14.10.1997,22.6.1998,28.8.1974,9.7.1977,27.5.1923,2.3.1947,12.12.1962,3.11.1987,7.6.1994,3.8.1998,27.8.1936,15.2.1988,26.4.1925,25.10.1966,26.3.1965,23.7.1928,25.9.1947,9.4.1935,23.10.1964,24.8.1997,15.3.1952,16.2.1947,28.12.1934,18.10.1948,11.2.1954,18.11.1973,9.12.1984,1.2.1952,2.8.1977,22.12.1930,24.8.1955,17.5.2000,23.4.1924,27.4.1948,27.1.1956,11.8.1963,9.6.1974,14.6.1977,15.7.1946,5.12.1979,11.3.1988,5.9.1934,22.1.1981,26.5.1933,18.11.1984,23.3.1950,22.9.1937,9.5.1991,10.2.1993,26.12.1999,21.5.1983,12.6.1954,18.10.1939,13.11.1951,15.9.1996,27.1.1968,23.7.1968,15.12.1992,7.7.2000,11.4.1985,4.7.1977,19.4.1986,2.8.1924,11.9.1934,19.9.1967,25.6.1967,26.9.1934,9.1.1952,28.11.1986,21.12.1947,2.12.1945,1.12.1934,8.10.1926,4.1.1997,25.2.1944,10.3.1976,15.8.1967,20.7.1997,10.12.1927,3.5.1985,5.8.1940,6.11.1950,2.1.1938,3.7.1924,6.11.1977,15.8.1983,21.11.1923,17.1.1935,17.9.1970,26.11.1956,25.10.1940,2.3.1991,2.11.1989,14.2.1924,2.3.1954,3.9.1970,26.11.1946,5.4.1976,18.5.1956,16.2.1925,28.10.1947,9.4.1982,25.7.1990,5.3.1981,1.8.1952,27.9.1987,14.9.1967,1.10.1984,16.12.1932,20.8.1948,24.3.1983,2.3.1986,21.6.1947,25.3.1924,24.2.1966,12.9.1921,19.11.1956,16.8.1948,6.10.1958,24.6.1933,28.4.1928,6.3.1989,17.2.1960,16.5.1961,1.11.1944,4.3.1982,11.3.1928,4.10.1937,14.2.1920,19.12.1995,4.10.1993,13.11.1967,2.4.1990,12.10.1994,23.4.1932,27.7.1931,11.11.1938,21.11.1978,10.1.1992,26.5.1935,4.3.1981,15.4.1925,2.8.1970,16.3.1998,16.3.1962,4.1.1944,10.12.1922,26.12.1945,23.11.1994,13.11.1992,25.11.1977,28.5.1944,9.5.1933,16.4.1975,17.7.1989,20.11.1964,16.2.1921,19.8.1954,10.2.1991,21.11.1985,9.1.1954,5.5.1941,27.12.1942,16.12.1953,19.7.1954,4.4.1994,16.11.1976,19.2.1983,4.10.1929,18.3.1996,6.9.1965,15.10.1972,10.10.1989,6.8.1930,27.10.1997,13.6.1990,6.3.1932,16.12.1983,27.3.1931,28.11.1964,21.9.1994,15.9.1958,4.9.1996,20.9.1999,22.1.1940,27.1.1970,5.12.1981,28.11.1984,7.12.1950,28.2.1985,25.1.1959,8.11.1996,9.6.1983,24.3.1965,25.9.1972,14.12.1960,26.3.1961,27.8.1944,5.3.1992,12.5.1957,26.4.1959,8.4.1959,4.12.1938,3.12.1926,26.12.1945,6.7.1951,25.12.1931,2.2.1970,26.12.1971,18.11.1953,25.1.1972,2.4.1982,21.10.1970,7.11.1954,17.12.1928,8.8.1960,26.2.1929,21.11.1991,7.2.1949,12.2.1939,24.6.1936,1.11.1976,18.3.1984,1.10.1943,11.5.1984,23.12.1994,5.3.1949,8.3.1942,28.6.1993,17.4.1965,19.3.1975,16.12.1998,2.9.1947,28.7.1947,14.11.1970,12.10.1975,26.7.1932,26.5.1936,18.8.1963,7.4.1942,27.4.1947,11.6.1939,8.1.1971,14.11.1925,15.10.1953,5.9.1974,7.6.1992,23.4.1936,10.7.1991,3.2.1951,18.8.1980,15.1.1922,5.5.1986,26.4.1971,10.8.1951,28.5.1976,12.1.1999,17.9.1943,21.11.1951,9.6.1932,12.10.1937,3.2.1940,20.8.1930,11.6.1944,8.11.1990,25.1.1961,3.12.1958,25.10.1994,17.4.1940,27.11.1972,3.4.1943,8.5.1934,2.3.1994,11.4.1960,24.4.1942,26.8.1944,9.1.1923,16.11.1950,23.5.1979,8.1.1921,13.7.1987,13.7.1940,5.2.1997,1.11.1980,10.3.1974,12.7.1965,5.11.1968,25.2.1947,6.5.1932,28.2.1961,24.1.1984,19.6.1955,12.7.1978";
	
	public int gruppe = 1;
	
	public int aktiv = 1;
    
    public DataFiller() {
    	
    }
    
    @Override public void run() {
    	
    	String[] nachnamen_array = this.nachnamen.split(",");
    	String[] vornamen_array = this.vornamen.split(",");
    	String[] geburtsdatum_array = this.geburtsdatum.split(",");
    	
    	System.out.println("Nachnamen: " + nachnamen_array.length);
    	System.out.println("Vornamen: " + vornamen_array.length);
    	System.out.println("Geburtsdaten: " + geburtsdatum_array.length);
    	
    	String[] loginData = EKitaManager.getInstance().settings.getMySQLData(EKitaManager.getInstance().settings.getServerType());
		
		MySQL dbconn = new MySQL(EKitaManager.getInstance());
		dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]);
		
		for(int i = 0; i < 1000; i++) {
    		dbconn.insert("INSERT INTO `Personenindex` "
					+ "(`PID` ,`Nachname` ,`Vorname` ,`Geburtsdatum` ,`Gruppe` ,`Aktiv`) "
					+ "VALUES "
					+ "(NULL ,  '" + nachnamen_array[i] + "',  '" + vornamen_array[i] + "',  '2011-12-24', '" + gruppe + "',  '" + aktiv + "');");
    	}
		
		dbconn.disconnect();
		dbconn = null;
    	
    }    

}
