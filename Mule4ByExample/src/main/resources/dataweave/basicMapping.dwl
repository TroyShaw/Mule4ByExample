%dw 2.0
output application/xml
---
User @(
	test: '', 
	test2: 'asd',
	test3: payload.lastName): {
		FirstName: payload.firstName,
		LastName: payload.lastName
}