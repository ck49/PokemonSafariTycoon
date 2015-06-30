import urllib2
import urllib
import sys
import re

def tf(str):
	if(str == '1'):
		return 'true'
	return 'false'
startNum = raw_input('up to what pokemon have you completed? ')
for i in range(721):
	if(i >= int(startNum)):
		page = urllib2.urlopen('http://veekun.com/dex/lookup?lookup=pokemon%3A' + str(i+1))
		x = page.read()
		#print page.read()
		#break
		Name = re.search(r'<title>.+#', x).group(0)[7:-6]
		Hatch = re.search(r'hatch_counter=.+', x).group(0)[14:-1]
		Catch  = re.search(r'capture_rate=.+', x).group(0)[13:-1]
		Description = re.search(r'<dt><img alt="X" src="/static/pokedex/images/versions/x.png" title="X" /></dt>\s+<dd><p>.+', x).group(0)[90:-9]
		Prey = raw_input('%s prey rank (between 1 and 4) '%(Name))
		if(int(prey) != 1 and int(prey)!= 4):
			Carnivore = tf(raw_input('%s Carnivore? true or false '%(Name)))
		if(int(prey)==1):
			Carnivore = 'false'
		if(int(prey) == 4):
			Carnivore = 'true'
		Rarity = raw_input('%s rarity between 1 and 10 '%(Name))
		temp = (raw_input('%s grass t/f '%(Name)), raw_input('lake tf? '), raw_input('mountain t/f? '))
		Grass = bool(int(temp[0]))
		Lake = bool(int(temp[1]))
		Mountain = bool(int(temp[2]))
		Price = raw_input('%s price '%(Name))
		writeTo = open(Name + '.java', 'a')
		urllib.urlretrieve("http://veekun.com/dex/media/pokemon/icons/%s.png"%(str(i+1)), str(i+1) + ".png")
		fileText = '''
		public class %s extends PokemonObject{

	    public %s(int pop){

	        carnivore = %s;
	        prey_rank = %s;
	        hatch = %s;
	        catch_rate = %s;
	        rarity = %s;
	        population = pop;
	        mountain = %s;
	        lake = %s;
	        grass = %s;
	        price = %s;
	        name = "%s"
	        description = "%s"
	        number = %s
	    }
		'''%(Name, Name, Carnivore, Prey, Hatch, Catch, Rarity, str(Mountain).lower(), str(Lake).lower(), str(Grass).lower(), Price, Name, Description, str(i+1))
		writeTo.write(fileText.replace(r"\xc3\xa9", 'e'))
		writeTo.close()
		print fileText.replace("\xc3\xa9", 'e')
	