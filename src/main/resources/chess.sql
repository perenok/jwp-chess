USE chessdb;

CREATE TABLE board (
roomName varchar(10),
position varchar(1000),
pieceName varchar(1000),
turn varchar(10),
PRIMARY KEY (roomName)
);