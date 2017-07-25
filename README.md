#fora-jaxrs

##Titre niveau 2


	@ApplicationPath("fora")
			public class ForaApplication extends Application {
				@Override
				public Set<Class<?>> getClasses() {
			
					Set<Class<?>> s = new HashSet<Class<?>>();
					s.add(UserResource.class);
					s.add(TopicRessource.class);
					s.add(CommentRessource.class);
					return s;
			}
		}
