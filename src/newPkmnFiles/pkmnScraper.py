import urllib2
import urllib
import sys
import re

def tf(str):
	if(str == '1'):
		return True
	return False
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
		Weight = re.search(r'.+lb <br/>', x).group(0)[1:-8]
		EvoNum = 1
		page1 = urllib2.urlopen(r"http://pokemon.wikia.com/wiki/List_of_Pok%C3%A9mon_by_Evolution")
		y = page1.readlines()
		prevline1 = ""
		prevline2 = ""
		n = 0
		for line in y:
			n = n + 1
			if(n > 1175):
				if(Name in line):
					if(r'href="/wiki/' in prevline1):
						Hatch = int(Hatch) * 2
						EvoNum = 2
						if(r'href="/wiki/' in prevline2):
							Hatch = Hatch * 2
							EvoNum = 3
				prevline2 = prevline1
				prevline1 = line		 

		
		if(i < 386):
			Habitat = re.search(r'habitats/.+p', x).group(0)[9:-2]
			(Mountain, Lake, Grass) = (False, False, False)
			if(Habitat == "cave" or Habitat == "rought terrain" or Habitat == "mountain"):
				Mountain = True
			elif(Habitat == "sea" or Habitat == "water's edge"):
				Lake = True
			elif(Habitat == "forest" or Habitat == "grassland"):
				Grass = True
			else:
				temp = (raw_input('%s grass t/f '%(Name)), raw_input('lake tf? '), raw_input('mountain t/f? '))
				Grass = bool(int(temp[0]))
				Lake = bool(int(temp[1]))
				Mountain = bool(int(temp[2]))

		else:
			temp = (raw_input('%s grass t/f '%(Name)), raw_input('lake tf? '), raw_input('mountain t/f? '))
			Grass = bool(int(temp[0]))
			Lake = bool(int(temp[1]))
			Mountain = bool(int(temp[2]))

		Carnivore = tf(raw_input('%s Carnivore? true or false '%(Name)))
		
		if((not Carnivore) and float(Weight) < 33):
			Prey = 1
		elif(float(Weight) < 88 or Name == "Beldum" or Name == "Nosepass" or Name == "Grotle" or Name == "Bergmite" or Name =="Munchlax"):
			Prey = 2
		elif((float(Weight) < 225 or Name == "Wailmer" or (not Carnivore)) and Name != "Garchomp"):
			Prey = 3
		else:
			Prey = 4


		
		Rarity = raw_input('%s rarity between 1 and 10 '%(Name))

		Price = (10*int(Rarity)*int(Catch)*int(Prey)/255) + (int(Rarity)*int(Rarity)*int(Rarity)*int(Rarity)*int(Prey)*int(Hatch)/(int(Catch)*10))

		
		
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
	        weight = %s;
	        price = %s;
	        name = "%s";
	        description = "%s";
	        number = %s;
	        evoNum = %s;

	    }
		'''%(Name, Name, str(Carnivore).lower(), Prey, Hatch, Catch, Rarity, str(Mountain).lower(), str(Lake).lower(), str(Grass).lower(), str(Weight), Price, Name, Description, str(i+1), str(EvoNum))
		#writeTo = open(Name + '.java', 'a')
		#writeTo.write(fileText.replace(r"\xc3\xa9", 'e'))
		#writeTo.close()'''
		#urllib.urlretrieve("http://veekun.com/dex/media/pokemon/icons/%s.png"%(str(i+1)), str(i+1) + ".png")
		print fileText.replace("\xc3\xa9", 'e')
	