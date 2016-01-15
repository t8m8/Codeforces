line = raw_input().split()

l = long(line[0])
r = long(line[1])
k = long(line[2])

a = 1
ans = []
while a <= r:
	if l <= a:
		ans.append(str(a))
	a *= k

n = len(ans)
print ' '.join(ans) or -1