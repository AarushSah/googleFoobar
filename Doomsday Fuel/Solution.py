'''
Google Foobar Doomsday Fuel Challenge

Keep in mind I did not know ANY Python 
before I started this challenge - my Java 
Solution wasn't working and I was rapidly 
running out of time, so I decided to just 
give my self a Python crash course. Hopefully, 
it worked.

Well, if you're seeing this, it did.
'''

# Why can you import numpy?
# I didn't expect this to work.
import numpy as np

def statedetector(matrix):
    act,ter=[],[]
    for rowN,row in enumerate(matrix):
        (act if sum(row) else ter).append(rowN)
    return(act,ter)


def simpleForm(B):
    B = B.round().astype(int).A1
    gcd = np.gcd.reduce(B)
    B = np.append(B, B.sum())
    return (B/gcd).astype(int)  


def solution(m):
    act, ter = statedetector(m)
    if 0 in ter:
        return [1] + [0]*len(ter[1:]) + [1]
    m = np.matrix(m, dtype=float)[act, :]
    cd = np.prod(m.sum(1))
    P = m/m.sum(1)
    Q, R = P[:, act], P[:, ter]
    I = np.identity(len(Q))
    N = (I - Q) ** (-1)
    B = N[0] * R * cd/np.linalg.det(N)
    return simpleForm(B)