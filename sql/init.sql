/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  fran
 * Created: Aug 27, 2024
 */

-- create database SteamGamePatchNotes;
-- use SteamGamePatchNotes;

create table Games(
    idSteamGame int primary key not null,
    [name] nvarchar(255) not null,
    steamURL nvarchar(255) not null,
    pictureURL nvarchar(255) null,
);

create table [Users](
    idUser int primary key identity(1,1) not null,
    username nvarchar(255) not null,
    password nvarchar(255) not null,
    isAdmin bit not null,
);

create table Authors(
    idAuthor int primary key identity(1,1) not null,
    [name] nvarchar(255) not null,
);

create table Patches(
    idPatch int primary key identity(1,1) not null,
    title nvarchar(255) not null,
    description nvarchar(455) not null,
    [link] nvarchar(255) not null,
    pubDate datetime not null,
    authorId int not null
    foreign key references Authors(idAuthor),
    gameId int not null
    foreign key references Games(idSteamGame),
);

-- Games CRUD Procedures

-- Users CRUD Procedures

-- Create User
CREATE OR ALTER PROC CreateUser
    @username NVARCHAR(255),
    @password NVARCHAR(255),
    @isAdmin BIT
AS
BEGIN
    INSERT INTO [Users] (username, [password], isAdmin)
    VALUES (@username, @password, @isAdmin)
END;


-- Read User
CREATE OR ALTER PROC ReadUser
    @idUser INT
AS
BEGIN
    SELECT * FROM [Users] WHERE idUser = @idUser
END;


-- Update User
CREATE OR ALTER PROC UpdateUser
    @idUser INT,
    @username NVARCHAR(255),
    @password NVARCHAR(255),
    @isAdmin BIT
AS
BEGIN
    UPDATE [Users]
    SET username = @username, [password] = @password, isAdmin = @isAdmin
    WHERE idUser = @idUser
END;


-- Delete User
CREATE OR ALTER PROC DeleteUser
    @idUser INT
AS
BEGIN
    DELETE FROM [Users] WHERE idUser = @idUser
END;


-- Authors CRUD Procedures

-- Create Author
CREATE OR ALTER PROC CreateAuthor
    @name NVARCHAR(255)
AS
BEGIN
    INSERT INTO Authors ([name])
    VALUES (@name)
END;


-- Read Author
CREATE OR ALTER PROC ReadAuthor
    @idAuthor INT
AS
BEGIN
    SELECT * FROM Authors WHERE idAuthor = @idAuthor
END;


-- Update Author
CREATE OR ALTER PROC UpdateAuthor
    @idAuthor INT,
    @name NVARCHAR(255)
AS
BEGIN
    UPDATE Authors
    SET [name] = @name
    WHERE idAuthor = @idAuthor
END;


-- Delete Author
CREATE OR ALTER PROC DeleteAuthor
    @idAuthor INT
AS
BEGIN
    DELETE FROM Authors WHERE idAuthor = @idAuthor
END;


-- Patches CRUD Procedures

-- Create Patch
CREATE OR ALTER PROC CreatePatch
    @title NVARCHAR(255),
    @description NVARCHAR(455),
    @link NVARCHAR(255),
    @pubDate DATETIME,
    @authorId INT,
    @gameId INT
AS
BEGIN
    INSERT INTO Patches (title, description, [link], pubDate, authorId, gameId)
    VALUES (@title, @description, @link, @pubDate, @authorId, @gameId)
END;


-- Read Patch
CREATE OR ALTER PROC ReadPatch
    @IdPatch INT
AS
BEGIN
    SELECT * FROM Patches WHERE IdPatch = @IdPatch
END;

CREATE OR ALTER PROC ReadPatches
    @IdPatch INT
AS
BEGIN
    SELECT * FROM Patches
END;

-- Create Game
CREATE OR ALTER PROC CreateGame
    @idSteamGame INT,
    @name NVARCHAR(255),
    @pictureURL NVARCHAR(255) = NULL,
    @steamURL NVARCHAR(255) = NULL
AS
BEGIN
    INSERT INTO Games (idSteamGame, [name], pictureURL, steamURL)
    VALUES (@idSteamGame, @name, @pictureURL, @steamURL)
END;


-- Read Game
CREATE OR ALTER PROC ReadGame
    @idSteamGame INT
AS
BEGIN
    SELECT * FROM Games WHERE idSteamGame = @idSteamGame
END;

CREATE OR ALTER PROC ReadGames
AS
BEGIN
    SELECT * FROM Games
END;

-- Update Game
CREATE OR ALTER PROC UpdateGame
    @idGame INT,
    @idSteamGame INT,
    @name NVARCHAR(255),
    @pictureURL NVARCHAR(255) = NULL,
    @steamURL NVARCHAR(255) = NULL

AS
BEGIN
    UPDATE Games
    SET idSteamGame = @idSteamGame, [name] = @name, pictureURL = @pictureURL, steamURL = @steamURL
    WHERE idGame = @idGame
END;


-- Delete Game
CREATE OR ALTER PROC DeleteGame
    @idSteamGame INT
AS
BEGIN
    DELETE FROM Games WHERE idSteamGame = @idSteamGame
END;


-- Users CRUD Procedures

-- Create User
CREATE OR ALTER PROC CreateUser
    @username NVARCHAR(255),
    @password NVARCHAR(255),
    @isAdmin BIT
AS
BEGIN
    INSERT INTO [Users] (username, [password], isAdmin)
    VALUES (@username, @password, @isAdmin)
END;


-- Read User
CREATE OR ALTER PROC ReadUser
    @idUser INT
AS
BEGIN
    SELECT * FROM [Users] WHERE idUser = @idUser
END;


-- Update User
CREATE OR ALTER PROC UpdateUser
    @idUser INT,
    @username NVARCHAR(255),
    @password NVARCHAR(255),
    @isAdmin BIT
AS
BEGIN
    UPDATE [Users]
    SET username = @username, [password] = @password, isAdmin = @isAdmin
    WHERE idUser = @idUser
END;


-- Delete User
CREATE OR ALTER PROC DeleteUser
    @idUser INT
AS
BEGIN
    DELETE FROM [Users] WHERE idUser = @idUser
END;


-- Authors CRUD Procedures

-- Create Author
CREATE OR ALTER PROC CreateAuthor
    @name NVARCHAR(255)
AS
BEGIN
    INSERT INTO Authors ([name])
    VALUES (@name)
END;


-- Read Author
CREATE OR ALTER PROC ReadAuthor
    @idAuthor INT
AS
BEGIN
    SELECT * FROM Authors WHERE idAuthor = @idAuthor
END;


-- Update Author
CREATE OR ALTER PROC UpdateAuthor
    @idAuthor INT,
    @name NVARCHAR(255)
AS
BEGIN
    UPDATE Authors
    SET [name] = @name
    WHERE idAuthor = @idAuthor
END;


-- Delete Author
CREATE OR ALTER PROC DeleteAuthor
    @idAuthor INT
AS
BEGIN
    DELETE FROM Authors WHERE idAuthor = @idAuthor
END;


-- Patches CRUD Procedures

-- Create Patch
CREATE OR ALTER PROC CreatePatch
    @title NVARCHAR(255),
    @description NVARCHAR(455),
    @link NVARCHAR(255),
    @pubDate DATETIME,
    @authorId INT,
    @gameId INT
AS
BEGIN
    INSERT INTO Patches (title, description, [link], pubDate, authorId, gameId)
    VALUES (@title, @description, @link, @pubDate, @authorId, @gameId)
END;


-- Read Patch
CREATE OR ALTER PROC ReadPatch
    @IdPatch INT
AS
BEGIN
    SELECT * FROM Patches WHERE IdPatch = @IdPatch
END;


-- Update Patch
CREATE OR ALTER PROC UpdatePatch
    @IdPatch INT,
    @title NVARCHAR(255),
    @description NVARCHAR(455),
    @link NVARCHAR(255),
    @pubDate DATETIME,
    @authorId INT,
    @gameId INT
AS
BEGIN
    UPDATE Patches
    SET title = @title, description = @description, [link] = @link, pubDate = @pubDate, authorId = @authorId, gameId = @gameId
    WHERE IdPatch = @IdPatch
END;


-- Delete Patch
CREATE OR ALTER PROC DeletePatch
    @IdPatch INT
AS
BEGIN
    DELETE FROM Patches WHERE IdPatch = @IdPatch
END;
    @title NVARCHAR(255),
    @description NVARCHAR(455),
    @link NVARCHAR(255),
    @pubDate DATETIME,
    @authorId INT,
    @gameId INT
AS
BEGIN
    UPDATE Patches
    SET title = @title, description = @description, [link] = @link, pubDate = @pubDate, authorId = @authorId, gameId = @gameId
    WHERE IdPatch = @IdPatch
END;


-- Delete Patch
CREATE OR ALTER PROC DeletePatch
    @IdPatch INT
AS
BEGIN
    DELETE FROM Patches WHERE IdPatch = @IdPatch
END;

-- sead data
exec createGame 
@idSteamGame = 1286580,
@name = 'Ship of Fools',
@pictureURL = 'https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1286580/capsule_231x87.jpg?t=1717445504',
@steamURL = 'https://steamcommunity.com/games/1286580' 

exec createAuthor
@name = 'hubgod'

declare @d datetime
set @d = CONVERT(DATETIME, '2024-05-21T18:00:25', 126)
exec createPatch
@title = 'Endless mode adjustment patch note',
@description = '<div class="bb_h1">Endless mode adjustment patch note</div><br><div class="bb_h2">Don''t Kill Them All</div><img src="https://clan.akamai.steamstatic.com/images/42083281/f588090851b9999f2e7a42553230adb09faa34c2.png" /><br><br>Our campaign for Don''t Kill Them All has 1000 backers! Check out our <a class="bb_link" href="https://www.kickstarter.com/projects/66192265/dont-kill-them-all?ref=49ji8g" target="_blank" rel="" >Kickstarter campaign</a> if you haven''t yet, or add us to your <a class="bb_link" href="https://store.steampowered.com/app/2322260/Dont_Kill_Them_All/" target="_blank" rel="" >wishlist</a> on Steam now!<br><br><div class="bb_h1">v1.4.1 patch notes</div><div class="bb_h2">New features:</div><ul class="bb_ul"><li><b>Game over screen skip button added</b>: You can now press the interact button during the scoring animation on the game over screen to fast-forward through it.</li></ul><div class="bb_h2">Balance changes:</div><ul class="bb_ul"><li> <b>Enemy HP Progression</b>: Adjusted the enemy HP progression curve in endless mode to provide a greater challenge and better match the players'' scaling power.<br><ul class="bb_ul"> <li> Loop 1 HP Multiplier: 2 → 3<br> </li><li> Loop 2 HP Multiplier: 5 → 10<br> </li><li> Loop 3 HP Multiplier: 9 → 25<br> </li><li> Loop 4 HP Multiplier: 14 → 50<br> </li><li> Loop 5 HP Multiplier: 20 → 100<br> </li><li> From Loop 6 onwards, the HP multiplier increases by 100 for each subsequent loop.</li></ul></li><li> Prevent self-inflicted damage from ejecting a plank from the ship<br></li><li>Golden bottle now increases paddle attack damage by 10%<br></li><li>Equipping multiple Light Fan trinkets now adds additional projectiles. Light Fan projectiles now synergizes with other paddle enhancements such as elemental effects, additional damage, size and speed.</li></ul> <img src="https://clan.akamai.steamstatic.com/images/42083281/4db37c47e4de6799aaccb33d5ffb823c5e65a532.gif" /><br><div class="bb_h2">Bug Fixes:</div><ul class="bb_ul"><li>Prevent some enemy looping sound to be played infinitely for the guest in an online game</li></ul>',
@link = 'https://steamcommunity.com/games/1286580/announcements/detail/4166470501218500057',
@pubDate = @d,
@authorId = @@identity,
@gameId = 1286580
;

exec createAuthor
@name = 'Multi Graine'

declare @d datetime
set @d = CONVERT(DATETIME, '2024-06-04T13:25:32', 126)
exec createPatch
@title = 'LAST CHANCE! 48h remaining to support our new game!',
@description = '<a class="bb_link" href="https://www.kickstarter.com/projects/66192265/dont-kill-them-all?ref=4f2une" target="_blank" rel="" ><img src="https://clan.akamai.steamstatic.com/images/42083281/b84e7c1d76754808450c9a136b6afabf5a85f792.png" /></a><br><br>Last month, we revealed Fika’s upcoming game, Don’t Kill Them All, and its Kickstarter campaign is about to reach the end! We’re super excited to announce that the project has successfully been funded and the game will be in YOUR hands in 2026!<br><br>Now is your <b>LAST CHANCE</b> to help us make this game come to life and grab some of the exclusive campaign rewards!<br><br><a class="bb_link" href="https://www.kickstarter.com/projects/66192265/dont-kill-them-all" target="_blank" rel="" >Visit our Kickstarter page</a><br><br><div onclick="javascript:ReplaceWithYouTubeEmbed( this );" data-youtube="ev5Iob6xLpE" class="sharedFilePreviewYouTubeVideo sizeFull"><img class="sharedFilePreviewYouTubeVideo sizeFull" src="https://steamcommunity.com/public/shared/images/responsive/youtube_16x9_placeholder.gif"/><iframe src="https://www.youtube.com/embed/ev5Iob6xLpE?fs=1&modestbranding=1&rel=0" allowFullScreen="1" frameBorder="0"></iframe></div><br>Don''t Kill Them All is a <b>turn-based strategy</b> and <b>base-building</b> game in which you <b>teach bloodthirsty Orcs to develop mindfulness</b> by discovering passions, navigating emotions, and developing healthy relationships. In this original twist on the strategy game genre, you loot everything that you don’t destroy at the end of each encounter, but beware, your attacks as well as your enemies’ have large areas of effect!<br><br><img src="https://clan.akamai.steamstatic.com/images/42083281/cb4e92da92a3dde71619ebf4b85ccd083a0ae492.gif" /><br><br><div class="bb_h3">Teach Orcs how to turn their passions into weapons!</div><img src="https://clan.akamai.steamstatic.com/images/42083281/7665f2b3fa332f771e172dbed7d6a32e3f6c8ca8.gif" /><br><br><div class="bb_h3">The little baby boar must be protected at all cost!</div><img src="https://clan.akamai.steamstatic.com/images/42083281/05f2622fbfcffaa874025c1ace28ded2264b6cb1.gif" /><br><br><div class="bb_h3">Protect the little baby boar <b>EVEN MORE</b> by adopting your own!</div><a class="bb_link" href="https://www.kickstarter.com/projects/66192265/dont-kill-them-all?ref=4f2une" target="_blank" rel="" ><img src="https://clan.akamai.steamstatic.com/images/42083281/b000e532f7ca8129533df4020fbc11ece64593e6.png" /></a><br><br>The whole team behind Ship of Fools has poured its heart into this one and it would mean the world to us if you like what you see and wish to support us in this new orc-filled adventure. Please consider backing our <a class="bb_link" href="https://www.kickstarter.com/projects/66192265/dont-kill-them-all" target="_blank" rel="" >kickstarter campaign</a> and adding the game to your wishlist.<br><br><a class="bb_link" href="https://store.steampowered.com/app/2322260/Dont_Kill_Them_All/" target="_blank" rel="" id="dynamiclink_0">https://store.steampowered.com/app/2322260/Dont_Kill_Them_All/</a><br><br><img src="https://clan.akamai.steamstatic.com/images/42083281/e26bf49d1cf41f5e372ae28ed396f06eaaead403.gif" />',
@link = 'https://steamcommunity.com/games/1286580/announcements/detail/4145079671533080023',
@pubDate = @d,
@authorId = @@identity,
@gameId = 1286580
;


Select * from games as g
inner join patches as p
on g.idSteamGame = p.gameId;

-- select * from patches

-- exec deleteGame @idSteamGame = 1286580

-- drop table Users
-- drop table Patches
-- drop table Authors;
-- drop table Games
