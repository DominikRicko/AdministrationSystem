// @ts-nocheck

db.createCollection("authority");
db.createCollection("securityGroup");
db.createCollection("userSecurityDetails");
db.createCollection("userInfo");

const bday = new Date("1998-08-28");

const expirationDate = new Date("2099-12-31");

const oneMonthFromNow = new Date();
oneMonthFromNow.setMonth(oneMonthFromNow.getMonth() + 1);

db.authority.insert({
	_id: ObjectId('700000000000000000000000'),
	"name": "READ_PROFILES", "description": "Allows user to read other users' profiles.",
	"groups": [
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000007') },
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000008') }
	]
});
db.authority.insert({
	_id: ObjectId('700000000000000000000001'), "name": "READ_GROUPS", "description": "Allows user to see groups and its members.",
	"groups": [
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000007') },
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000008') }
	]
});
db.authority.insert({
	_id: ObjectId('700000000000000000000002'), "name": "READ_PRIVILEGES", "description": "Allows user to see different authorities",
	"groups": [
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000007') },
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000008') }
	]
});
db.authority.insert({
	_id: ObjectId('700000000000000000000003'),
	"name": "WRITE_PROFILES",
	"description": "Allows user to edit other people's profiles.",
	"groups": [{ $ref: "securityGroup", $id: ObjectId('700000000000000000000008') }]
});
db.authority.insert({
	_id: ObjectId('700000000000000000000004'),
	"name": "WRITE_GROUPS",
	"description": "Allows user to edit/add new groups to the system.",
	"groups": [{ $ref: "securityGroup", $id: ObjectId('700000000000000000000008') }]
});
db.authority.insert({
	_id: ObjectId('700000000000000000000005'),
	"name": "WRITE_PRIVILEGES",
	"description": "Allows user to add new and edit existing privileges in the system",
	"groups": [{ $ref: "securityGroup", $id: ObjectId('700000000000000000000008') }]
});


db.securityGroup.insert({
	_id: ObjectId('700000000000000000000006'),
	"name": "Users",
	"authorities": [],
	"members": [{$ref: 'userSecurityDetails', $id: ObjectId('700000000000000000000012')}]
});
db.securityGroup.insert({
	_id: ObjectId('700000000000000000000007'),
	"name": "Managers",
	"authorities": [
		{ $ref: 'authority', $id: ObjectId('700000000000000000000000') },
		{ $ref: 'authority', $id: ObjectId('700000000000000000000001') },
		{ $ref: 'authority', $id: ObjectId('700000000000000000000002') }
	],
	"members": [{$ref: 'userSecurityDetails', $id: ObjectId('700000000000000000000011')}]
});
db.securityGroup.insert({
	_id: ObjectId('700000000000000000000008'),
	"name": "Admins",
	"authorities": [
		{ $ref: 'authority', $id: ObjectId('700000000000000000000000') },
		{ $ref: 'authority', $id: ObjectId('700000000000000000000001') },
		{ $ref: 'authority', $id: ObjectId('700000000000000000000002') },
		{ $ref: 'authority', $id: ObjectId('700000000000000000000003') },
		{ $ref: 'authority', $id: ObjectId('700000000000000000000004') },
		{ $ref: 'authority', $id: ObjectId('700000000000000000000005') },
	],
	"members": [
		{ $ref: 'userSecurityDetails', $id: ObjectId('700000000000000000000009') },
		{ $ref: 'userSecurityDetails', $id: ObjectId('700000000000000000000011') }
	]
});


db.userSecurityDetails.insert({
	_id: ObjectId('700000000000000000000009'),
	"username": "dricko",
	"email": "dominik.ricko@gmail.com",
	"passwordHash": "$2a$10$RzsfOZiGr/P2sRQPM/QQ6.nCHOXDaFuPbR46gMZPMutOGek2OCOIW",
	"enabled": true,
	"accountExpirationDate": ISODate(expirationDate.toISOString()),
	"credentialsExpirationDate": ISODate(expirationDate.toISOString()),
	"groups": [
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000008') }
	],
	"user": { $ref: "userInfo", $id: ObjectId('700000000000000000000010') }
});
db.userSecurityDetails.insert({
	_id: ObjectId('700000000000000000000010'),
	"username": "admin",
	"email": "admin@admin.com",
	"passwordHash": "$2a$10$Lr.npBAvYJMwJYb3uSFqkuReRoKvo/QfSFaz4YoGvHxZZ1Gjp61Li",
	"enabled": true,
	"accountExpirationDate": ISODate(oneMonthFromNow.toISOString()),
	"credentialsExpirationDate": ISODate(oneMonthFromNow.toISOString()),
	"groups": [
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000008') }
	]
});
db.userSecurityDetails.insert({
	_id: ObjectId('700000000000000000000011'),
	"username": "manager",
	"email": "manager@manager.com",
	"passwordHash": "$2a$10$QQi9Y4/JQVCkgeObKJVEE.myHLl5or0I5Ht/5uF89PoMq7YEtQPSi",
	"enabled": true,
	"accountExpirationDate": ISODate(oneMonthFromNow.toISOString()),
	"credentialsExpirationDate": ISODate(oneMonthFromNow.toISOString()),
	"groups": [
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000007') }
	]
});
db.userSecurityDetails.insert({
	_id: ObjectId('700000000000000000000012'),
	"username": "user",
	"email": "user@user.com",
	"passwordHash": "$2a$10$tepgLiQTai5X3neZKsZ7P.mGmn75nQOFkJpg05.dtJrcJiJ3.4iWq",
	"enabled": true,
	"accountExpirationDate": ISODate(oneMonthFromNow.toISOString()),
	"credentialsExpirationDate": ISODate(oneMonthFromNow.toISOString()),
	"groups": [
		{ $ref: "securityGroup", $id: ObjectId('700000000000000000000006') }
	]
});


db.userInfo.insert({
	_id: ObjectId('700000000000000000000010'),
	"name": "Dominik",
	"surname": "Ričko",
	"birthdate": ISODate(bday.toISOString()),
	"address": "Ruđera Boškovića, 21, Petrinja 44250",
	"userSecurity": { $ref: "userSecurityDetails", $id: ObjectId('700000000000000000000009') }
});