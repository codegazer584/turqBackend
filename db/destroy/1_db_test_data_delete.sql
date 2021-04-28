IF EXISTS(SELECT * FROM legislation)
      DELETE FROM legislation;
IF EXISTS(SELECT * FROM payments)
      DELETE FROM payments;
IF EXISTS(SELECT * FROM contests)
      DELETE FROM contests;
IF EXISTS(SELECT * FROM users)
      DELETE FROM users;
