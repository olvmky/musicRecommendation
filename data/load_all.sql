LOAD DATA LOCAL INFILE 'placeholder/filename.csv' INTO TABLE Genres FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'placeholder/filename.csv' INTO TABLE Albums FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES (AlbumId, @AlbumName) SET AlbumName = TRIM(BOTH '"' FROM @AlbumName);

LOAD DATA LOCAL INFILE 'placeholder/filename.csv' INTO TABLE Tracks FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'placeholder/filename.csv' INTO TABLE Artists FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES;

LOAD DATA LOCAL INFILE 'placeholder/filename.csv' INTO TABLE TrackArtists FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 ROWS (TrackId, ArtistId) SET RelationId = NULL;

LOAD DATA LOCAL INFILE 'placeholder/generated/filename.csv' INTO TABLE Users FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES (Username, Password, FirstName, LastName, Email, Phone);

LOAD DATA LOCAL INFILE 'placeholder/generated/filename.csv' INTO TABLE Comments FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES (CommentId, Created, Content, Username, TrackId);

LOAD DATA LOCAL INFILE 'placeholder/generated/filename.csv' INTO TABLE MoodTag FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES (MoodId, Mood, TrackId, Username);

LOAD DATA LOCAL INFILE 'placeholder/generated/filename.csv' INTO TABLE PlaylistTracks FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES (RecordId, PlaylistId, Track_Id, Position);

LOAD DATA LOCAL INFILE 'placeholder/generated/filename.csv' INTO TABLE LikeAndDislike FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES (ReviewId, Created, LikeOrDislike, TrackId, Username) SET LikeOrDislike = IF(@LikeOrDislike = 'true', 1, 0);

LOAD DATA LOCAL INFILE 'placeholder/generated/filename.csv' INTO TABLE ListeningHistory FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES (HistoryId, Created, TimesListened, Duration, TrackId, UserName);

LOAD DATA LOCAL INFILE 'placeholder/generated/filename.csv' INTO TABLE Playlists FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES (PlaylistId, Created, @IsPublic, Username, Name, Description) SET IsPublic = IF(@IsPublic = 'true', 1, 0);
