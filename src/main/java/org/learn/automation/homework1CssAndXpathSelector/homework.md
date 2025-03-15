### Task 1: CSS Selector 
1. Մտնում եք էս լինքով, ու նենց css selector եք գրում, որով վերցնեք ձախի վրայի գտնվող բոլոր checkbox ֆիլտրները, մասնավորապես a թեգով էլեմենտները։
2. Վերևի խնդիրը լուծել միայն Brands-ի տակ գտնվող ֆիլտրների համար։ 2 առանձին սելեկտորներ պետքա գրեք։
3. Հետո մտնում եք մի լինքով, ու սելեկտոր րք գրում, որը կընտրի միայն"Add to Cart" button-ը։
### solution
    1. a:has(input[type='checkbox'])
    2. *[id*='brands'] a:has(input[type='checkbox'])
    3. *[id='add-to-cart-button']

----------------------------------------------------------------------------

### Task 2: Xpath Selector
1. Մտնում եք https://www.flashfootball.com/ լինքով, վերևի աջ անկյունում Search--ի button կա, էդ եք լոքեյթ անում։
2. Նույն էջում, գտնում եք տվյալ օրվա բոլոր խաղերը։ Ձեր գրածը սելեկտորը պետքա match անի յուրաքանչյուր օրվա հանդիպման ամբողջ ինֆոն՝ [տվյալ տողը էլի](https://files.slack.com/files-pri/T0755P230MB-F08HNDZUA06/image.png)։
3. Գրում եք սելեկտոր, որը կգտնի ներքևի նկարում նշված tab-ի բոլոր էլեմենտներին։Օգտագործել DOM-ի հիերարխիան դրանք լոքեյթ անելու համար։
###    solution
    1. //*[@id='search-window']//*[@class='searchIcon']
    2. //div[contains(@class, 'sportName')]//div[contains(@class, 'event__match')]
    3. //*[@id="footballTopMenu"]//*[@class='topMenuSpecific']//a
