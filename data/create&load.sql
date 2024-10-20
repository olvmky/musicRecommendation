#create tables: 
# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS MusicApplication;
USE MusicApplication;

DROP TABLE IF EXISTS PlaylistTracks;
DROP TABLE IF EXISTS PlayLists;
DROP TABLE IF EXISTS ListeningHistory;
DROP TABLE IF EXISTS MoodTag;
DROP TABLE IF EXISTS LikeAndDislike;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS TrackArtists;
DROP TABLE IF EXISTS Artists;
DROP TABLE IF EXISTS Tracks;
DROP TABLE IF EXISTS Genres;
DROP TABLE IF EXISTS Albums; 

CREATE TABLE Albums (
    AlbumId INT AUTO_INCREMENT PRIMARY KEY,
    AlbumName VARCHAR(255)
);
 
CREATE TABLE Genres (
    GenreId INT AUTO_INCREMENT PRIMARY KEY,
    GenreName VARCHAR(255)
);

CREATE TABLE Tracks (
    TrackId VARCHAR(255) PRIMARY KEY,
    TrackName VARCHAR(1000),
    AlbumId INT,
    GenreId INT,
    
    
    Popularity INT,
    DurationMs INT,
    Explicit BOOLEAN,
    Danceability DECIMAL(15,5),
    Energy DECIMAL(15,10),
    Pitch INT,
    Loudness DECIMAL(15,5),
    Modality INT,
    Speechiness DECIMAL(15,5),
    Acousticness DECIMAL(15,10),
    Instrumentalness DECIMAL(15,10),
    Liveness DECIMAL(15,10),
    Valence DECIMAL(15,10),
    Tempo DECIMAL(15,5),
    TimeSignature INT,
    
    FOREIGN KEY (AlbumId) REFERENCES Albums(AlbumId) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (GenreId) REFERENCES Genres(GenreId) ON UPDATE CASCADE ON DELETE SET NULL
);
 
CREATE TABLE Artists (
    ArtistId INT AUTO_INCREMENT PRIMARY KEY,
    ArtistName VARCHAR(255)
);

CREATE TABLE TrackArtists (
		RelationId INT AUTO_INCREMENT PRIMARY KEY,
    TrackId VARCHAR(255),
    ArtistId INT,
    FOREIGN KEY (TrackId) REFERENCES Tracks(TrackId) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (ArtistId) REFERENCES Artists(ArtistId) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Users (
    UserName VARCHAR(255) PRIMARY KEY,
    Password VARCHAR(255),
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    Phone VARCHAR(255)
);

CREATE TABLE Comments (
    CommentId INT AUTO_INCREMENT PRIMARY KEY,
    Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Content VARCHAR(255) NOT NULL,
    UserName VARCHAR(255),
    TrackId VARCHAR(255),
    FOREIGN KEY (UserName) REFERENCES Users(UserName) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (TrackId) REFERENCES Tracks(TrackId) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE LikeAndDislike (
    ReviewId INT AUTO_INCREMENT PRIMARY KEY,
    Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LikeOrDislike BOOLEAN,
    TrackId VARCHAR(255),
    UserName VARCHAR(255),
    FOREIGN KEY (UserName) REFERENCES Users(UserName) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (TrackId) REFERENCES Tracks(TrackId) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE MoodTag (
    MoodId INT AUTO_INCREMENT PRIMARY KEY,
    Mood ENUM('HAPPY', 'SAD', 'RELAXED', 'EXCITED', 'ROMANTIC', 'ANGRY'),
    TrackId VARCHAR(255),
    UserName VARCHAR(255),
    FOREIGN KEY (UserName) REFERENCES Users(UserName) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (TrackId) REFERENCES Tracks(TrackId) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ListeningHistory (
    HistoryId INT AUTO_INCREMENT PRIMARY KEY,
    Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TimesListened INT,
    Duration INT,
    TrackId VARCHAR(255),
    UserName VARCHAR(255),
    FOREIGN KEY (UserName) REFERENCES Users(UserName) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (TrackId) REFERENCES Tracks(TrackId) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Playlists (
    PlaylistId INT AUTO_INCREMENT PRIMARY KEY,
    Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    IsPublic BOOLEAN,
    UserName VARCHAR(255),
    Name VARCHAR(255),
    Description VARCHAR(255),
    FOREIGN KEY (UserName) REFERENCES Users(UserName) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE PlaylistTracks (
    RecordId INT AUTO_INCREMENT PRIMARY KEY,
    PlayListId INT,
    Track_Id VARCHAR(255),
    Position INT,
    FOREIGN KEY (PlayListId) REFERENCES PlayLists(PlayListId) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Track_Id) REFERENCES Tracks(TrackId) ON UPDATE CASCADE ON DELETE CASCADE
);


# load data
LOAD DATA LOCAL INFILE '/Users/s/Desktop/5200/PM2/genres.csv' INTO TABLE Genres
FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/Users/s/Desktop/5200/PM2/albums.csv' INTO TABLE Albums
FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/Users/s/Desktop/5200/PM2/tracks.csv' INTO TABLE Tracks
FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n'
IGNORE 1 LINES;
 
LOAD DATA LOCAL INFILE '/Users/s/Desktop/5200/PM2/artists.csv' INTO TABLE Artists
FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/Users/s/Desktop/5200/PM2/track_artists.csv' INTO TABLE TrackArtists
FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' 
IGNORE 1 ROWS (TrackId, ArtistId) SET RelationId = NULL;

SELECT 
    (SELECT COUNT(*) FROM artists) AS ArtistCount,
    (SELECT COUNT(*) FROM albums) AS AlbumCount,
    (SELECT COUNT(*) FROM genres) AS GenreCount,
    (SELECT COUNT(*) FROM tracks) AS TrackCount,
    (SELECT COUNT(*) FROM trackartists) AS TrackArtistCount;
